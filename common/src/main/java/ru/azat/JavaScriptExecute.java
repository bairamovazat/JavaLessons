package ru.azat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptExecute {
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        engine.eval("function f() { return 1; };" +
                "var a = 3;");
    }

    public static void main(String[] args) throws ScriptException {
        JavaScriptExecute javaScriptExecute = new JavaScriptExecute();
        javaScriptExecute.test();
    }
}
