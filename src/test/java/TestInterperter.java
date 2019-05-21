import org.junit.Assert;
import org.junit.Test;
import parser.Parser;
import structure.Program;

public class TestInterperter {
    private final Parser parser = new Parser();

    private void testProgram(String input, int expectedValue, boolean shouldFail) {
        try {
            Program program = parser.parse(input);
            Assert.assertEquals(expectedValue, program.runProgram());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(shouldFail);
            return;
        }
        Assert.assertFalse(shouldFail);
    }

    @Test
    public void testEmptyFunctionDefinitionList() {
        testProgram("(2+2)", 4, false);
        testProgram("(2+((3*4)/5))", 4, false);
    }

    @Test
    public void testIfExpression() {
        testProgram("[((10+20)>(20+10))]?{1}:{0}", 0, false);
        testProgram("[((10+20)=(20+10))]?{1}:{0}", 1, false);
    }

    @Test
    public void testFunctionDefinitionList() {
        testProgram(
                "g(x)={(f(x)+f((x/2)))}\n" +
                "f(x)={[(x>1)]?{(f((x-1))+f((x-2)))}:{x}}\n" +
                "g(10)",
                60, false);
    }

    @Test
    public void testInvalidInput() {
        testProgram("1 + 2 + 3 + 4 + 5", -1, true);
        testProgram(
                "f(x)={y}\n" +
                "f(10)",
                -1, true);
        testProgram(
                "g(x)={f(x)}\n" +
                "g(10)",
                -1, true);
        testProgram(
                "g(x)={(x+1)}\n" +
                "g(10,20)",
                -1, true);
        testProgram(
                "g(a,b)={(a/b)}\n" +
                "g(10,0)",
                -1, true);
    }
}
