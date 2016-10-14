package io.github.gitbucket.markedj.token;

public class MathToken implements Token {

    @Override
    public String getType() {
        return "MathToken";
    }

    private String code;

    public MathToken(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
