package com.tickeron.test.web.functional.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface class for common variable storage for all steps
 */
public class ParamsAndVariablesSteps extends Steps {

    @Autowired
    Environment environment;

    // Storage for values
    private Map<String, String> tempValues = new HashMap<String, String>();
    private Map<String, String> testParams = new HashMap<String, String>();

    private static Logger log = LoggerFactory.getLogger("com.tickeron.test.web.functional.steps");
    public Map<String, String> getVariableStorage() {
        return tempValues;
    }
    public Map<String, String> getTestParamsStorage() {
        return testParams;
    }

    public String substituteParamsAndVariables(String input) {
        input = substituteTestParams(input);
        return substituteTestVariables(input);

    }

    private String substituteTestParams(String input) {
        for (String key: getTestParamsStorage().keySet()) {
            String keyPattern = "%{" + key + "}";
            if (input.contains(keyPattern)) input = input.replace(keyPattern, testParams.get(key));
        }
        return input;
    }

    private String substituteTestVariables(String input) {
        for (String key: getVariableStorage().keySet()) {
            String keyPattern = "${" + key + "}";
            if (input.contains(keyPattern)) input = input.replace(keyPattern, getVariableStorage().get(key));
        }
        return input;
    }

    @Given("Test params are: $table")
    public void setTestParams(ExamplesTable table) {
        testParams.clear();
        testParams.put("__dynamic__localdate_now", String.valueOf(LocalDate.now()));
        for (int i = 0; i < table.getRowCount(); i++) {
            String name = table.getRow(i).get("name");
            String value = table.getRowAsParameters(i, false).valueAs("value", String.class);
            testParams.put(name, value);
        }
    }


    @Given("Set param $name value $value")
    public void setParamValue(String name,  String value) {
        getTestParamsStorage().put(name, value);

    }

}

