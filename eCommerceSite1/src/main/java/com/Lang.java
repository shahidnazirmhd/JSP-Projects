package com;



public class Lang {
private String lang="en";
private static Lang instance;
public static synchronized Lang getInstance(){
    if (null!=instance){
        return instance;
    }else {
        instance=new Lang();
        return instance;
    }
}
public String getLang() {
	return lang;
}

public void setLang(String lang) {
	this.lang = lang;
}

}
