package com.github.javaparser.printer.lexicalpreservation;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

public class Issue4074Test {
    @Test
    public void testRemoveImportRetainsNewLine() {
        // Set up a CompilationUnit with multiple imports and a newline
        CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse("import a;\nimport b;\n\nclass X {}"));

        // Remove the second import
        cu.getImports().remove(1);

        // Verify that the newline is retained
        String expectedOutput = "import a;\n\nclass X {}";
        assertEquals(expectedOutput, LexicalPreservingPrinter.print(cu));
    }

    @Test
    public void testRemoveSingleImportNoExtraNewLine() {
        // Set up a CompilationUnit with two imports and no extra newline
        CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse("import a;\nimport b;\nclass X {}"));

        // Remove the second import
        cu.getImports().remove(1);

        // Verify that no extra newline is added
        String expectedOutput = "import a;\nclass X {}";
        assertEquals(expectedOutput, LexicalPreservingPrinter.print(cu));
    }


    @Test
    public void testRemoveAllImportsRetainsNewLineBeforeClass() {
        // Set up a CompilationUnit with multiple imports and newlines
        CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse("import a;\nimport b;\n\nclass X {}"));

        // Remove all imports
        cu.getImports().clear();

        // Verify that the newline before the class is retained
        String expectedOutput = "\nclass X {}";
        assertEquals(expectedOutput, LexicalPreservingPrinter.print(cu));
    }

}
