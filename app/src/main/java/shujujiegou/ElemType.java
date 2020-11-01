package shujujiegou;

public class ElemType {
    public String data;
    public ElemType(){
        data = "";
    }
    public ElemType(String s){
        data = s;
    }
    public ElemType(double d){
        data = String.valueOf(d);
    }
    //清空字符串
    public void clearData(){
        data = "";
    }

    //追加一个字符
    public void addOne(String a){
        data+=a;
    }
    //追加一个字符
    public void addOne(char a){
        data+=a;
    }

    public Double toDouble(){
        return Double.valueOf(data);
    }
    //删除最后一个字符
    public void delOne(){
        if(data.endsWith("#")){
            data = data.substring(0,data.length()-1);
        }
        data = data.substring(0,data.length()-1);
    }

}
