package vmtranslator;

import java.io.IOException;
import java.io.Writer;

/**
 * Output Hack ASM for VM code.
 * 
 * @author djb
 * @version 1.1 by Aleksej Bratkovskij (ab2323)
 */
public class CodeWriter {
    // Temporary registers for intermediate calculations,
    // should they be required by the translator.
    private static final String R13 = "R13", R14 = "R14", R15 = "R15";
    private int eqCount = 0;
    private int ltCount = 0;
    private int gtCount = 0;

    // Where the translation is written.
    private final Writer writer;
    // Current file being processed.
    private String currentFilename;
    
    /**
     * Create a CodeWriter to output to the given file.
     * @param writer Where to write the code.
     */
    public CodeWriter(Writer writer)
    {
        this.writer = writer;
    }
    
    /**
     * Translation of a new file.
     * @param filename The input file name.
     */
    public void setFilename(String filename)
    {
        this.currentFilename = filename;
    }




    /**
     * Translate the given arithmetic command.
     * @param command The command to be translated.
     * @throws java.io.IOException
     */
    public void writeArithmetic(String command)
            throws IOException
    {
        switch(command) {
            // Binary arithmetic operators.
            case "add":
                Dpoping();
                output("D=M+D");
                output("@SP");
                output("A=M-1");
                output("M=D");
                break;

            case "and":
                Dpoping();
                output("D=D&M");
                output("@SP");
                output("A=M-1");
                output("M=D");

                break;
            case "or":
                Dpoping();
                output("D=D|M");
                output("@SP");
                output("A=M-1");
                output("M=D");
                break;

            case "sub":
                Dpoping();
                output("D=M-D");
                output("@SP");
                output("A=M-1");
                output("M=D");
                break;

            // Unary operators.
            case "neg":
                output("@SP");
                output("A=M");
                output("A=A-1");
                output("M=-M");
                break;

            case "not":
                output("@SP");
                output("A=M");
                output("A=A-1");
                output("M=!M");
                break;

            // Relational operators.
            case "eq":
                Dpoping();
                output("D=D-M");
                output("@EQUAL" + eqCount);
                output("D;JEQ");
                output("@SP");
                output("A=M-1");
                output("M=0");
                output("@END_EQ"+eqCount);
                output("0;JMP");
                output("(EQUAL"+eqCount+")");
                output("@SP");
                output("A=M-1");
                output("M=-1");
                output("(END_EQ"+eqCount+")");
                eqCount++;
                break;

            case "lt":
                Dpoping();
                output("D=D-M");
                output("@LESS"+ ltCount);
                output("D;JGT");
                output("@SP");
                output("A=M-1");
                output("M=0");
                output("@END_LT"+ltCount);
                output("0;JMP");
                output("(LESS"+ltCount+")");
                output("@SP");
                output("A=M-1");
                output("M=-1");
                output("(END_LT"+ltCount+")");
                ltCount++;
                break;

            case "gt":
                Dpoping();
                output("D=D-M");
                output("@MORE"+ gtCount);
                output("D;JLT");
                output("@SP");
                output("A=M-1");
                output("M=0");
                output("@END_GT"+gtCount);
                output("0;JMP");
                output("(MORE"+ gtCount +")");
                output("@SP");
                output("A=M-1");
                output("M=-1");
                output("(END_GT"+gtCount+")");
                gtCount++;
                break;

            default:
                throw new IllegalStateException("Unrecognised arithmetic command: " + command);
        }
    }


    /**
     * Translate the given push or pop command.
     * @param command The command to be translated.
     * @param segment The segment to be accessed.
     * @param index   The index within segment.
     * @throws java.io.IOException
     */
    public void writePushPop(CommandType command, String segment, int index)
            throws IOException
    {
        if(null == command) {
            throw new IllegalStateException("Invalid command in writePushPop: " +
                    command);
        }
        else {
            switch (command) {
                case C_PUSH:
                    if (segment.equals("constant")) output("@" + index + "\n" + "D=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");

                    else if (segment.equals("local")) output(pushes("LCL",index,false));
                    else if (segment.equals("argument")) output(pushes("ARG",index,false));
                    else if (segment.equals("this")) output(pushes("THIS",index,false));
                    else if (segment.equals("that")) output(pushes("THAT",index,false));
                    else if (segment.equals("temp")) output(pushes("R5", index + 5,false));
                    else if (segment.equals("pointer") && index == 0) output(pushes("THIS",index,true));
                    else if (segment.equals("pointer") && index == 1) output(pushes("THAT",index,true));
                    else if (segment.equals("static")) output(pushes(String.valueOf(16 + index),index,true));
                    break;

                case C_POP:
                    if (segment.equals("local")) output(pops("LCL",index,false));
                    else if (segment.equals("argument")) output(pops("ARG",index,false));
                    else if (segment.equals("this")) output(pops("THIS",index,false));
                    else if (segment.equals("that")) output(pops("THAT",index,false));
                    else if (segment.equals("temp")) output(pops("R5", index + 5,false));
                    else if (segment.equals("pointer") && index == 0) output(pops("THIS",index,true));
                    else if (segment.equals("pointer") && index == 1) output(pops("THAT",index,true));
                    else if (segment.equals("static")) output(pops(String.valueOf(16 + index),index,true));
                    break;

                default:
                    throw new IllegalStateException("Invalid command in writePushPop: " +
                            command);
            }
        }
    }

    /**
     *
     * @param seg segment of command we're interested in
     * @param i index
     * @param direct is it direct adressing
     * @return constructed string for C_PUSH command
     */
    private String pushes(String seg,int i, boolean direct){
        String npc = (direct)? "" : "@" + i + "\n" + "A=D+A\nD=M\n";

        return "@" + seg + "\n" +
                "D=M\n"+
                npc +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n";

    }

    /**
     *
     * @param seg part of full command
     * @param i index
     * @param direct is it direct adressing
     * @return constructed string for C_POP command
     */
    private String pops(String seg, int i, boolean direct){
        String npc = (direct)? "D=A\n" : "D=M\n@" + i + "\nD=D+A\n";

        return "@" + seg + "\n" +
                npc +
                "@R13\n" +
                "M=D\n" +
                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@R13\n" +
                "A=M\n" +
                "M=D\n";
    }

    public void Dpoping()
            throws IOException
    {
        output("@SP");
        output("M=M-1");
        output("A=M");
        output("D=M");
        output("A=A-1");
    }

    /**
     * Write the given text as a comment.
     * @param text the text to output.
     * @throws IOException 
     */
    public void writeComment(String text)
            throws IOException
    {
        output("// " + text);
    }
    
    /**
     * Close the output file.
     * @throws IOException 
     */
    public void close()
            throws IOException
    {
        try (writer) {
            String endOfProgram = "THATS_ALL_FOLKS";
            output("@" + endOfProgram);
            output(String.format("(%s)", endOfProgram));
            output("0;JMP");
        }
    }
    
    /**
     * Output the given string with an indent and
     * terminate the current line.
     * @param s The string to output.
     * @throws IOException 
     */
    private void output(String s)
            throws IOException
    {
        writer.write("    ");
        writer.write(s);
        writer.write('\n');
    }
    
}
