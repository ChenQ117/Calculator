package shujujiegou;

import android.util.Log;

/**
 * 顺序栈
 * 栈顶指针在栈顶元素的上方
 */

public class Stack {
    ElemType[] data;
//    String[]  data;//数据域
    int top;//栈顶
    int base;//栈尾
    public Stack(){
        data = new ElemType[100];
        top = 0;
        base = 0;
    }

    //进栈
    public void Push(ElemType a){
        data[top++] = a;
    }

    //出栈
    public ElemType Pop(){
        ElemType s = new ElemType();
        if(!isEmpty()){
            s = data[--top];
        }
        return s;
    }

    //获得栈顶元素
    public ElemType getTop(){
        ElemType s = new ElemType();
        if (!isEmpty()){
            s = data[top-1];
        }
        return s;
    }

    //输出所有元素
    public void showElem(){
        for (int i = 0;i<top;i++){
            Log.d("Stack",data[i].data+" ");
        }
    }

    //栈是否为空
    public boolean isEmpty(){
        return top==base;
    }
}
