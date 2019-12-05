package net.vivans.emsmw.emsmw.util;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Getter
public class Calculator {
    private static final Map<String, BiFunction<Calculator, Calculator, Calculator>> operators = new HashMap<>();
    private static int count = 0;

    static {
        operators.put("sum",(a, b) -> new Calculator(a.no.add(b.no)));
        operators.put("avg",(a, b) -> new Calculator(a.no.multiply(new BigDecimal(count-1)).add(b.no).divide(new BigDecimal(count), 6 , BigDecimal.ROUND_CEILING)));
        operators.put("max", (a,b) -> new Calculator(a.no.compareTo(b.no) < 0 ? b.no : a.no));
        operators.put("current", (a,b) -> new Calculator(b.no));
        operators.put("none", (a,b) -> new Calculator(b.no));
    }

    private BigDecimal no;

    public Calculator(BigDecimal no){
        this.no = no;
    }

    public Calculator calculate(String expression, Calculator calculator, int existFileCount){
        count = existFileCount;
        BiFunction<Calculator, Calculator, Calculator> operator = operators.get(expression);
        if(operator == null){
            throw new IllegalArgumentException();
        }
        return operator.apply(this, calculator);
    }
}
