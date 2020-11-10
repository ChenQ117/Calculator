package com.example.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import shujujiegou.ElemType;
import shujujiegou.Queue;
import shujujiegou.Stack;

public class MainActivity extends AppCompatActivity {

    Button bt_0,bt_1,bt_2,bt_3,bt_4,bt_5,bt_6,bt_7,bt_8,bt_9,bt_point;
    Button bt_equal,bt_clear,bt_left,bt_right,bt_plus,bt_minus,bt_mutiple,bt_divide,bt_mod,bt_del;
    TextView tv1,tv2,tv3,tv4;
    Stack mStack1 = new Stack();//运算符栈
    Stack mStack2 = new Stack();
    Queue mQueue = new Queue();//表达式队列
    ElemType mElemType = new ElemType();//按键输入的表达式
    final double MIN = 1e-320;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Click();
        InitStack(mStack1);
        InitStack(mStack2);
        bt_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("#");
                String s = tv1.getText().toString().trim()+"#";//s为输入的表达式
                boolean flag_0 = IsMatch(s);//判断括号是否匹配
                if(!flag_0){
                    Toast.makeText(MainActivity.this,"括号不匹配",Toast.LENGTH_SHORT).show();
                }else {
                    String string1 = s;
                    String string2 = s;
                    ElemType sumLast ;//后缀的计算结果
                    ElemType sumMid ;//中缀的计算结果
                    // 中缀转后缀
                    String str = transfrom(string1);
                    tv2.setText(str);

                    // 计算中缀转后缀
                    try {
                        sumLast = sumOfLast(mQueue);
                        String sum = sumLast.data;
                        double x = sumLast.toDouble();
                        int y = (int)x;
                        if (Math.abs(y-x)<MIN){
                            sum = String.valueOf(y);
                        }
                        Log.d("str",str+"--------------");

                        tv1.setText(sum);
                        str = str +" = "+sum;
                        tv2.setText(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"除0错误",Toast.LENGTH_SHORT).show();
                    }

                    //中缀表达式的计算
                    try {
                        sumMid = sumOfMid(string2);
                        String sum = sumMid.data;
                        double x = sumMid.toDouble();
                        int y = (int)x;
                        if (Math.abs(y-x)<MIN){
                            sum = String.valueOf(y);
                        }
                        tv1.setText(sum);
                        string2 = string2.substring(0,string2.length()-1)+" = "+sum;
                        tv3.setText(string2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"除0错误",Toast.LENGTH_SHORT).show();
                    }
                    mElemType .clearData();
                }

            }
        });
    }

    //初始化成员变量
    private void Init(){
        bt_0 = findViewById(R.id.bt_0);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
        bt_3 = findViewById(R.id.bt_3);
        bt_4 = findViewById(R.id.bt_4);
        bt_5 = findViewById(R.id.bt_5);
        bt_6 = findViewById(R.id.bt_6);
        bt_7 = findViewById(R.id.bt_7);
        bt_8 = findViewById(R.id.bt_8);
        bt_9 = findViewById(R.id.bt_9);
        bt_point = findViewById(R.id.bt_point);
        bt_equal = findViewById(R.id.bt_equal);
        bt_clear = findViewById(R.id.bt_clear);
        bt_left = findViewById(R.id.bt_left);
        bt_right = findViewById(R.id.bt_right);
        bt_plus = findViewById(R.id.bt_plus);
        bt_minus = findViewById(R.id.bt_minus);
        bt_mutiple = findViewById(R.id.bt_mutiple);
        bt_divide = findViewById(R.id.bt_divide);
        bt_mod = findViewById(R.id.bt_mod);
        bt_del = findViewById(R.id.bt_del);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        tv1.setText(pref.getString("tv_1",""));
        tv2.setText(pref.getString("tv_2",""));
        tv3.setText(pref.getString("tv_3",""));
    }

    //初始化栈,使栈底为"#"
    private void InitStack(Stack stack){
        ElemType elemType = new ElemType("#");
        stack.Push(elemType);
    }
    //所有按钮的点击事件
    public void Click(){
//        mElemType.addOne(tv1.getText().toString().trim());
        bt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("0");
                tv1.append("0");
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("1");
                tv1.append("1");
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("2");
                tv1.append("2");
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("3");
                tv1.append("3");
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("4");
                tv1.append("4");
            }
        });
        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("5");
                tv1.append("5");
            }
        });
        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("6");
                tv1.append("6");
            }
        });
        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("7");
                tv1.append("7");
            }
        });
        bt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("8");
                tv1.append("8");
            }
        });
        bt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("9");
                tv1.append("9");
            }
        });
        bt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("+");
                tv1.append("+");
            }
        });
        bt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("-");
                tv1.append("-");
            }
        });
        bt_mutiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("*");
                tv1.append("*");
            }
        });
        bt_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("/");
                tv1.append("/");
            }
        });
        bt_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("^");
                tv1.append("^");
            }
        });
        bt_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne(".");
                tv1.append(".");
            }
        });
        bt_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne(".");
                tv1.append(".");
            }
        });
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne("(");
                tv1.append("(");
            }
        });
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.addOne(")");
                tv1.append(")");
            }
        });
        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType = new ElemType(tv1.getText().toString().trim());
                mElemType.delOne();
                tv1.setText(mElemType.data);
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mElemType.clearData();
                tv1.setText(mElemType.data);
//                tv1.setText("");
            }
        });
    }

    //括号是否匹配
    public boolean IsMatch(String s){
        Stack stack = new Stack();
//        InitStack(stack);
        boolean flag = true;
        for (int i = 0;i<s.length();i++){
            char ch = s.charAt(i);
            switch (ch){
                case '(':
                    ElemType e = new ElemType(ch);
                    stack.Push(e);
                    break;
                case ')':
                    if (stack.isEmpty()){//如果栈空则说明没有与该右括号匹配的左括号了
                        flag = false;
                        break;
                    }
                    stack.Pop();
                    break;
            }
        }
        if (flag&&stack.isEmpty()){
            return true;
        }
        return false;
    }
    //比较中缀转后缀时的运算符优先级
    public char Precede(ElemType e,char ch){
        // 优先级：同级中左边的大于右边，不同级中“*”、"/"、"%"大于‘+’、‘-’，‘#’最小
        // 当‘（’属于左边即已进栈时其优先级最小，当‘（’属于右边即尚未进栈时其优先级最大
        // 当右边是‘）’而左边不是‘（’时，左边的优先级最大
        // 当右边是‘）’而左边是‘（’时，两边优先级相等，当左右两边都是‘#’时，优先级相等
        if("#".equals(e.data)&&ch!='#'||ch=='('){
            return '<';
        }
        if(ch=='#'&&!"#".equals(e.data)||ch==')'&&!"(".equals(e.data)){
            return '>';
        }

        if ("^".equals(e.data)){
            return '>';
        }
        if (ch == '^'){
            return '<';
        }
        if("*".equals(e.data)||"/".equals(e.data)||"%".equals(e.data)){
            return '>';
        }
        if(ch == '*'||ch=='/'||ch=='%'){
            return '<';
        }
        if ("+".equals(e.data)||"-".equals(e.data)){
            return '>';
        }
        if("(".equals(e.data)&&ch!=')'){
            return '<';
        }
        return '=';


    }
    char Priority(char x,char y){ //比较运算符优先级
        if( (x=='('&&y==')')|| (x=='#' && y == '#') )
            return '=';
        if(x=='#')
            return '<';
        if(y=='('|| x=='(')
            return '<';
        if(x == ')' || y == ')'||y == '#')
            return '>';
        if(y == '+'||y == '-')
            return '>';
        if(x=='+'||x=='-')
            return '<';
        else
            return '>';
    }

    //中缀转后缀
    public String transfrom(final String string){

        int i = 0;
        String str ="";
        //将操作数和运算符分离
        //负数的特殊处理
        ElemType elemType = new ElemType();//操作数对象
        char ch1 = string.charAt(i);
        if (ch1 =='-'||ch1 == '+'){//判断第一个数是否为负数
            elemType.addOne(ch1);
            i++;
        }

        for (;i<string.length();i++){
            boolean flag = false;//用于判断第i个字符是否为操作数，从而确定是否需要入队列
            char ch = string.charAt(i);
            while (ch>='0'&&ch<='9'||ch=='.'){//判断一个操作数的位数
                flag = true;
                elemType.addOne(ch);
                ch = string.charAt(++i);
            }
            if (flag){
                mQueue.EnQueue(elemType);//操作数入队列
                Log.d("mQueue","------------------中缀转后缀mQueue-------------------");
                mQueue.showElem();
                str = str +" "+elemType.data;
            }


            //如果不符合while循环中的判断条件，则说明此时的ch为运算符，应该进运算符栈
            ElemType elemType2 = new ElemType();//运算符对象
            elemType2.addOne(ch);

            //对类似于（-9）这样的负数的特殊处理
            elemType = new ElemType();//下一个操作数对象
            if(ch=='('&&string.charAt(i+1)=='-'||ch=='('&&string.charAt(i+1)=='+'){
                mStack1.Push(elemType2);//将‘（’压入栈
                Log.d("mStack1","------------------中缀转后缀mStack1-------------------");
                mStack1.showElem();
                i++;
                elemType.addOne(string.charAt(i));
                continue;//此时下一个字符一定为数字，就可以不用进行下面的入栈操作了
            }


            //要求栈顶为优先级最大的元素，如果ch比栈顶大，则直接入，如果ch与栈顶同级或者比栈顶小则将栈顶元素弹出，直到ch比栈顶元素大为止
            while ((!"#".equals(mStack1.getTop().data))&&Precede(mStack1.getTop(),ch)=='>'){
                ElemType type = mStack1.Pop();
                mQueue.EnQueue(type);
                Log.d("mQueue","------------------中缀转后缀mQueue-------------------");
                mQueue.showElem();
                str = str +" "+type.data;
            }
            //如果栈顶元素比ch小，此时可入栈
            if (Precede(mStack1.getTop(),ch)=='<'){
                mStack1.Push(elemType2);
                Log.d("mStack1","------------------中缀转后缀mStack1-------------------");
                mStack1.showElem();
            }

            //碰到右括号时，左括号出栈
            if (Precede(mStack1.getTop(),ch)=='='&&"(".equals(mStack1.getTop().data)){
                mStack1.Pop();
                Log.d("mStack1","------------------中缀转后缀mStack1-------------------");
                mStack1.showElem();
            }
        }
        return str;
    }

    //计算后缀表达式
    public ElemType sumOfLast(Queue queue) throws Exception {
        Stack stack = new Stack();
        InitStack(stack);

        ElemType sumLast = new ElemType();//后缀的计算结果
        ElemType elemType1 ;
        ElemType elemType2;
        while (!queue.IsEmpty()){
            double sum;
            ElemType elemType =null;
            switch (mQueue.getOneElem().data){
                case "+":
                    mQueue.DeQueue();
                    elemType1 = stack.Pop();
                    elemType2 = stack.Pop();
                    sum = elemType2.toDouble()+elemType1.toDouble();
                    elemType = new ElemType(sum);
                    break;
                case "-":
                    mQueue.DeQueue();
                    elemType1 = stack.Pop();
                    elemType2 = stack.Pop();
                    sum = elemType2.toDouble()-elemType1.toDouble();
                    elemType = new ElemType(sum);
                    break;
                case "*":
                    mQueue.DeQueue();
                    elemType1 = stack.Pop();
                    elemType2 = stack.Pop();
                    sum = elemType2.toDouble()*elemType1.toDouble();
                    elemType = new ElemType(sum);
                    break;
                case "/":
                    mQueue.DeQueue();
                    elemType1 = stack.Pop();
                    elemType2 = stack.Pop();

                        double x1 =elemType2.toDouble();
                        double y1 =elemType1.toDouble();
                        if(Math.abs(y1-0)<MIN){
                            throw  new Exception();
                        }
                        sum = x1/y1;
                        elemType = new ElemType(sum);

                    break;
                case "^":
                    mQueue.DeQueue();
                    elemType1 = stack.Pop();
                    elemType2 = stack.Pop();
                    sum = Math.pow(elemType2.toDouble(),elemType1.toDouble());
                    elemType = new ElemType(sum);
                    break;

                    default:
                        stack.Push(mQueue.DeQueue());
                        break;

            }
            Log.d("mQueue","------------------计算后缀mQueue-------------------");
            mQueue.showElem();
            if (!(elemType == null)){
                stack.Push(elemType);
                Log.d("stack","------------------计算后缀stack-------------------");
                stack.showElem();
            }
        }
        sumLast = stack.Pop();
        return sumLast;
    }

    //中缀表达式计算
    public ElemType sumOfMid(final String string) throws Exception {
        Stack stack1 = new Stack();//操作数栈
        Stack stack2 = new Stack();//运算符栈
        InitStack(stack1);
        InitStack(stack2);
        Log.d("Mid",string);
        ElemType sumMid=new ElemType();//中缀的计算结果

        ElemType elemType1 = new ElemType();
        int i = 0;
        if (string.charAt(i)=='-'||string.charAt(i) == '+'){//负数的特殊处理
            elemType1.addOne('-');
            i++;
        }
        for(;i<string.length();i++){
            char ch = string.charAt(i);
            boolean flag = false;
            while (ch<='9'&&ch>='0'||ch=='.'){
                flag = true;
                elemType1.addOne(ch);
                ch = string.charAt(++i);
            }
            if (flag){
                stack1.Push(elemType1);
                Log.d("stack1","--------------计算中缀stack1-----------------------");
                stack1.showElem();
            }

            //如果不符合while循环中的判断条件，则说明此时的ch为运算符，应该进运算符栈
            ElemType elemType2 = new ElemType();//运算符对象
            elemType2.addOne(ch);

            //对类似于（-9）这样的负数的特殊处理
            elemType1 = new ElemType();//下一个操作数对象
            if(ch=='('&&string.charAt(i+1)=='-'||ch=='('&&string.charAt(i+1)=='+'){
                stack2.Push(elemType2);//将‘（’压入栈
                Log.d("stack2","--------------计算中缀stack2-----------------------");
                stack2.showElem();
                i++;
                elemType1.addOne(string.charAt(i));
                continue;//此时下一个字符一定为数字，就可以不用进行下面的入栈操作了
            }

            //要求栈顶为优先级最大的元素，如果ch比栈顶大，则直接入，如果ch与栈顶同级或者比栈顶小则将栈顶元素弹出，直到ch比栈顶元素大为止
            while ((!"#".equals(stack2.getTop().data))&&Precede(stack2.getTop(),ch)=='>'){
                ElemType type = stack2.Pop();
                ElemType elem1;
                ElemType elem2;
                double sum = 0;
                switch (type.data){
                    case "+":
                        elem1 = stack1.Pop();
                        elem2 = stack1.Pop();
                        sum = elem2.toDouble()+elem1.toDouble();
                        break;
                    case "-":
                        elem1 = stack1.Pop();
                        elem2 = stack1.Pop();
                        sum = elem2.toDouble()-elem1.toDouble();
                        break;
                    case "*":
                        elem1 = stack1.Pop();
                        elem2 = stack1.Pop();
                        sum = elem2.toDouble()*elem1.toDouble();
                        break;
                    case "/":
                        elem1 = stack1.Pop();
                        elem2 = stack1.Pop();
                        double d1 = elem1.toDouble();
                        double d2 = elem2.toDouble();
                        if (Math.abs(d1-0)<=MIN){
                            throw new Exception();
                        }
                        sum = d2/d1;
                        break;
                    case "^":
                        elem1 = stack1.Pop();
                        elem2 = stack1.Pop();
                        sum = Math.pow(elem2.toDouble(),elem1.toDouble());
                        break;
                }
                stack1.Push(new ElemType(sum));
                Log.d("stack1","--------------计算中缀stack1-----------------------");
                stack1.showElem();

            }

            //如果栈顶元素比ch小，此时可入栈
            if (Precede(stack2.getTop(),ch)=='<'){
                stack2.Push(elemType2);
                Log.d("stack2","----------------计算中缀stack2---------------------");
                stack2.showElem();
            }

            //碰到右括号时，左括号出栈
            if (Precede(stack2.getTop(),ch)=='='&&"(".equals(stack2.getTop().data)){
                stack2.Pop();
                Log.d("stack1","----------------计算中缀stack2---------------------");
                stack2.showElem();
            }
        }
        sumMid = stack1.Pop();
        Log.d("stack1","--------------计算中缀stack1-----------------------");
        stack1.showElem();
        Log.d("sumMid",sumMid.data);
        return sumMid;
    }

    @Override
    protected void onStop() {
        super.onStop();
        String tv_1 = tv1.getText().toString().trim();
        String tv_2 = tv2.getText().toString().trim();
        String tv_3 = tv3.getText().toString().trim();
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("tv_1",tv_1);
        editor.putString("tv_2",tv_2);
        editor.putString("tv_3",tv_3);
        editor.apply();
    }
}
