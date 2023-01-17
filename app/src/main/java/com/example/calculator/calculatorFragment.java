package com.example.calculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class calculatorFragment extends Fragment
{
    private Button AC,plus_minus,percent,divided,x,minus,plus,equal,point,_0,_1,_2,_3,_4,_5,_6,_7,_8,_9;
    private TextView txv_result,txv_calculator;
    private boolean lastIsOperator;
    private String lastOperator = " ";
    private double firstNum = 0;
    private double secondNum = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculator_fragment,container,false);

        init(view);

        return view;
    }
    private void init(View view)
    {
        AC = view.findViewById(R.id.ac);
        AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        plus_minus = view.findViewById(R.id.plus_minus);
        plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        percent = view.findViewById(R.id.percent);
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        divided = view.findViewById(R.id.divided);
        divided.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        x = view.findViewById(R.id.x);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        minus = view.findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        plus = view.findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        equal = view.findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _0 = view.findViewById(R.id._0);
        _0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _1 = view.findViewById(R.id._1);
        _1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _2 = view.findViewById(R.id._2);
        _2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _3 = view.findViewById(R.id._3);
        _3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _4 = view.findViewById(R.id._4);
        _4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _5 = view.findViewById(R.id._5);
        _5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _6 = view.findViewById(R.id._6);
        _6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _7 = view.findViewById(R.id._7);
        _7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _8 = view.findViewById(R.id._8);
        _8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        _9 = view.findViewById(R.id._9);
        _9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        point = view.findViewById(R.id.point);
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        txv_result = view.findViewById(R.id.txv_result);
        txv_calculator = view.findViewById(R.id.txv_calculator);

        txv_result.setText("");
        txv_calculator.setText("");
        lastIsOperator = false;
        firstNum = 0;
        secondNum = 0;
        lastOperator = "";
    }

    public void click(View view)
    {
        String currentT = txv_calculator.getText().toString();
        String operatorNum = "";
        if(currentT.equals("0"))
        {
            txv_calculator.setText("");
        }
        operatorNum = txv_calculator.getText().toString();
        if(!lastOperator.equals(""))
        {
            int index = operatorNum.lastIndexOf(lastOperator);
            operatorNum = operatorNum.substring(index+1);
        }
        switch (view.getId())
        {
            case R.id._0:
                txv_calculator.setText(txv_calculator.getText().toString()+"0");
                lastIsOperator = false;
                break;
            case R.id._1:
                txv_calculator.setText(txv_calculator.getText().toString()+"1");
                lastIsOperator = false;
                break;
            case R.id._2:
                txv_calculator.setText(txv_calculator.getText().toString()+"2");
                lastIsOperator = false;
                break;
            case R.id._3:
                txv_calculator.setText(txv_calculator.getText().toString()+"3");
                lastIsOperator = false;
                break;
            case R.id._4:
                txv_calculator.setText(txv_calculator.getText().toString()+"4");
                lastIsOperator = false;
                break;
            case R.id._5:
                txv_calculator.setText(txv_calculator.getText().toString()+"5");
                lastIsOperator = false;
                break;
            case R.id._6:
                txv_calculator.setText(txv_calculator.getText().toString()+"6");
                lastIsOperator = false;
                break;
            case R.id._7:
                txv_calculator.setText(txv_calculator.getText().toString()+"7");
                lastIsOperator = false;
                break;
            case R.id._8:
                txv_calculator.setText(txv_calculator.getText().toString()+"8");
                lastIsOperator = false;
                break;
            case R.id._9:
                txv_calculator.setText(txv_calculator.getText().toString()+"9");
                lastIsOperator = false;
                break;
            case R.id.point:
                txv_calculator.setText(txv_calculator.getText().toString()+".");
                lastIsOperator = false;
                break;
            case R.id.ac:
                txv_result.setText("");
                txv_calculator.setText("");
                lastIsOperator = false;
                firstNum = 0;
                secondNum = 0;
                lastOperator = "";
                break;
            case R.id.divided:
                if(TextUtils.isEmpty(txv_calculator.getText()) || lastIsOperator && !lastOperator.equals("="))
                {
                    return;
                }
                operatorCalc(operatorNum,"÷");
                lastIsOperator = true;
                txv_calculator.setText(txv_calculator.getText().toString()+"÷");
                lastOperator = "÷";
                break;
            case R.id.x:
                if(TextUtils.isEmpty(txv_calculator.getText()) || lastIsOperator && !lastOperator.equals("="))
                {
                    return;
                }
                operatorCalc(operatorNum,"X");
                lastIsOperator = true;
                txv_calculator.setText(txv_calculator.getText().toString()+"X");
                lastOperator = "X";
                break;
            case R.id.minus:
                if(TextUtils.isEmpty(txv_calculator.getText()) || lastIsOperator && !lastOperator.equals("="))
                {
                    return;
                }
                operatorCalc(operatorNum,"-");
                lastIsOperator = true;
                txv_calculator.setText(txv_calculator.getText().toString()+"-");
                lastOperator = "-";
                break;
            case R.id.plus:
                if(TextUtils.isEmpty(txv_calculator.getText()) || lastIsOperator && !lastOperator.equals("="))
                {
                    return;
                }
                operatorCalc(operatorNum,"+");
                lastIsOperator = true;
                txv_calculator.setText(txv_calculator.getText().toString()+"+");
                lastOperator = "+";
                break;
            case R.id.percent:
                if(TextUtils.isEmpty(txv_calculator.getText()) || lastIsOperator && !lastOperator.equals("="))
                {
                    return;
                }
                operatorCalc(operatorNum,"%");
                lastIsOperator = true;
                txv_calculator.setText(txv_calculator.getText().toString()+"%");
                lastOperator = "%";
                break;
            case R.id.plus_minus:
                double d = firstNum;
                if(TextUtils.isEmpty(txv_calculator.getText()) || TextUtils.isEmpty(txv_result.getText()) || lastIsOperator && !lastOperator.equals("="))
                {
                    return;
                }
                if(Double.parseDouble(txv_result.getText().toString()) > 0)
                {
                    txv_result.setText("-"+txv_result.getText().toString());
                    firstNum = Double.parseDouble(txv_result.getText().toString());
                }
                else if(Double.parseDouble(txv_result.getText().toString()) < 0)
                {
                    txv_result.setText(String.valueOf(d));
                }
                break;
            case R.id.equal:
                if(TextUtils.isEmpty(lastOperator))
                {
                    return;
                }
                operatorResult(operatorNum);
                secondNum = 0;
                lastOperator = "=";
                lastIsOperator = true;
                txv_calculator.setText(txv_calculator.getText().toString()+"=");
                txv_result.setText(String.valueOf(firstNum));
                break;
        }
    }
    private void operator(String operatorNum)
    {
        if(secondNum != 0)
        {
            switch (lastOperator) {
                case "÷":
                    secondNum = secondNum / Double.parseDouble(operatorNum);
                    firstNum = firstNum / secondNum;
                    break;
                case "X":
                    secondNum = secondNum * Double.parseDouble(operatorNum);
                    firstNum = firstNum * secondNum;
                    break;
                case "-":
                    secondNum = secondNum - Double.parseDouble(operatorNum);
                    firstNum = firstNum - secondNum;
                    break;
                case "+":
                    secondNum = secondNum + Double.parseDouble(operatorNum);
                    firstNum = firstNum + secondNum;
                    break;
                case "%":
                    secondNum = secondNum * 0.01;
                    //firstNum = firstNum + secondNum;
                    break;
            }
        }
        else
        {
            switch (lastOperator) {
                case "÷":
                    if (!operatorNum.isEmpty()) {
                        firstNum = firstNum / Double.parseDouble(operatorNum);
                    } else {
                        firstNum = 1.0;
                    }
                    break;
                case "X":
                    if (!operatorNum.isEmpty()) {
                        firstNum = firstNum * Double.parseDouble(operatorNum);
                    } else {
                        firstNum = firstNum * firstNum;
                    }
                    break;
                case "-":
                    if (!operatorNum.isEmpty()) {
                        firstNum = firstNum - Double.parseDouble(operatorNum);
                    } else {
                        firstNum -= firstNum;
                    }
                    break;
                case "+":
                    if (!operatorNum.isEmpty()) {
                        firstNum = firstNum + Double.parseDouble(operatorNum);
                    } else {
                        firstNum += firstNum;
                    }
                    break;
                case "%":
                    if (!operatorNum.isEmpty()) {
                        firstNum = firstNum * 0.01;
                    } else {
                        firstNum *= 0.01;
                    }
                    break;
            }
        }
    }
    private void operatorResult(String operatorNum)
    {
        operator(operatorNum);
    }
    private void operatorCalc(String operatorNum , String currentOperator)
    {
        if(TextUtils.isEmpty(lastOperator))
        {
            firstNum = Double.parseDouble(operatorNum);
            return;
        }
        if(lastOperator.equals(currentOperator))
        {
            switch (lastOperator) {
                case "÷":
                    firstNum = firstNum / Double.parseDouble(operatorNum);

                    break;
                case "X":
                    firstNum = firstNum * Double.parseDouble(operatorNum);

                    break;
                case "-":
                    firstNum = firstNum - Double.parseDouble(operatorNum);

                    break;
                case "+":
                    firstNum = firstNum + Double.parseDouble(operatorNum);

                    break;
                case "%":
                    firstNum = firstNum *0.01;

                    break;
            }
            return;
        }
        operator(operatorNum);
    }
}
