package md2html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

import md2html.markHtml.AllElements;
import md2html.markHtml.Code;
import md2html.markHtml.Emphasis;
import md2html.markHtml.Header;
import md2html.markHtml.Link;
import md2html.markHtml.Paragraph;
import md2html.markHtml.Strikeout;
import md2html.markHtml.Strong;
import md2html.markHtml.Text;

public class Md2Html {
    public static boolean isMark(char chr) {
        return chr == '*' || chr == '_' || chr == '-' || chr == '`' || chr == '\\' || chr == '[' || chr == ']';
    }

    public static String isHtmlSymbol(char chr) {
        return switch (chr) {
            case '<' -> "&lt;";
            case '>' -> "&gt;";
            case '&' -> "&amp;";
            default -> Character.toString(chr);
        };
    }

    public static AllElements toHtmlPiece(String mark, String link, List<AllElements> htmlPieces) {
        return switch (mark) {
            case "*", "_" -> new Emphasis(htmlPieces);
            case "**", "__" -> new Strong(htmlPieces);
            case "--" -> new Strikeout(htmlPieces);
            case "`" -> new Code(htmlPieces);
            case "[" -> new Link(link, htmlPieces);
            default -> new Text("");
        };
    }

    public static void addParagraph(int head, Deque<AllElements> paragraphs, Deque<List<AllElements>> parts) {
        List<AllElements> partsNew = new ArrayList<>(parts.peek());
        if (head == 0) {
            paragraphs.add(new Paragraph(partsNew));
        } else {
            paragraphs.add(new Header(partsNew, head));
        }
    }

    public static int isHeader(String line, StringBuilder textInParagraph) {
        int headerNum = 0;
        while (headerNum < line.length() && line.charAt(headerNum) == '#' && headerNum < 6) {
            headerNum++;
        }
        if (0 < headerNum && headerNum < line.length() && Character.isWhitespace(line.charAt(headerNum))) {
            textInParagraph.append(line.substring(headerNum + 1));
        } else {
            headerNum = 0;
            textInParagraph.append(line);
        }
        return headerNum;
    }

    public static boolean spaceCheck(int idxInMark, StringBuilder textInParagraph, String str) {
        return ((idxInMark + str.length() >= textInParagraph.length() || Character.isWhitespace(textInParagraph.charAt(idxInMark + str.length()))) &&
                (idxInMark - 1 <= 0 || Character.isWhitespace(textInParagraph.charAt(idxInMark - 1))));
    }

    public static boolean isDash(int idxInMark, StringBuilder textInParagraph, Character chr) {
        return chr == '-' && idxInMark + 1 < textInParagraph.length() &&
                !(chr == textInParagraph.charAt(idxInMark + 1));
    }

//    public static boolean matchMark(String mark, String lastMark) {
//        String endMark = switch (mark) {
//            case "*", "-", "**", "__", "--", "`" -> mark;
//            case ">>" -> "<<";
//            case "{{" -> "}}";
//            default -> "";
//        };
//        return endMark.equals(lastMark);
//    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File(args[0]));
            Deque<AllElements> paragraphs = new ArrayDeque<>();
            StringBuilder textInParagraph = new StringBuilder();
            try {
                StringBuilder link = new StringBuilder();
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    while (scan.hasNextLine() && line.isEmpty()) {
                        line = scan.nextLine();
                    }

                    int headerNum = isHeader(line, textInParagraph);

                    while (scan.hasNextLine() && !line.isEmpty()) {
                        line = scan.nextLine();
                        if (!line.isEmpty()) {
                            textInParagraph.append("\n");
                        }
                        textInParagraph.append(line);
                    }

                    Deque<List<AllElements>> parts = new ArrayDeque<>();
                    Deque<String> marks = new ArrayDeque<>();
                    parts.push(new ArrayList<>());
                    int idxInMark = 0;
                    StringBuilder textInMark = new StringBuilder();
                    while (idxInMark < textInParagraph.length()) {
                        char chr = textInParagraph.charAt(idxInMark);
                        if (!isMark(chr)) {
                            textInMark.append(isHtmlSymbol(chr));
                        } else if (chr == '\\') {
                            textInMark.append(textInParagraph.charAt(idxInMark + 1));
                            idxInMark++;
                        } else if (isDash(idxInMark, textInParagraph, chr)) {
                            textInMark.append(chr);
                        } else {
                            if (chr == ']') {
                                chr = '[';
                                link = new StringBuilder();
                                idxInMark += 2;
                                while (textInParagraph.charAt(idxInMark) != ')') {
                                    link.append(textInParagraph.charAt(idxInMark));
                                    idxInMark++;
                                }
                            }
                            String mark = Character.toString(chr);
                            if (idxInMark + 1 < textInParagraph.length() && chr == textInParagraph.charAt(idxInMark + 1)) {
                                mark += textInParagraph.charAt(idxInMark);
                                idxInMark++;
                            }
                            if (spaceCheck(idxInMark, textInParagraph, mark)) {
                                textInMark.append(mark);
                                idxInMark++;
                                continue;
                            }

                            parts.peek().add(new Text(textInMark.toString()));
                            textInMark.setLength(0);

                            if (marks.isEmpty() || !(marks.peek().equals(mark))) {
                                parts.push(new ArrayList<>());
                                marks.push(mark);
                            } else {
                                List<AllElements> lastPart = parts.pop();
                                parts.peek().add(toHtmlPiece(mark, link.toString(), lastPart));
                                marks.pop();
                            }
                        }
                        idxInMark++;
                    }
                    parts.peek().add(new Text(textInMark.toString()));
                    textInMark.setLength(0);

                    addParagraph(headerNum, paragraphs, parts);
                    textInParagraph.setLength(0);
                }
            } finally {
                scan.close();
            }
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                ));
                try {
                    for (AllElements elem : paragraphs) {
                        elem.toHtml(writer);
                        writer.newLine();
                    }
                } finally {
                    writer.close();
                }

            } catch (FileNotFoundException e) {
                System.out.println("File does not exist: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Output file writer error: " + e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist: " + e.getMessage());
        }
    }
}
