package com.test;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;

@MadvocAction
public class IndexAction {

	 @In
     String name;

     @Out
     String value;

     @Action("/index.html")
     public String world() {
         System.out.println("HelloAction.world " + name);
         value = "Hello World!";
         return "beetl:ok.html";
     }
}