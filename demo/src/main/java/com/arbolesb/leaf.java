package com.arbolesb;

import com.arbolesb.key;

public class leaf{
    public int numKeys;
    public int maxKeys;
    public int minKeys;
    public int grade;
    public key[] keys; 
    public key currentKey;  
    public leaf[] childrenLeafs;
    public boolean isLeaf; 



    public leaf(int grade) {
        this.maxKeys = grade - 1;
        this.minKeys = (int) Math.floor((grade - 1) / 2);
        this.keys = new key[grade - 1];
        this.numKeys = 0;
        this.currentKey = null;
        this.childrenLeafs = new leaf[grade+1];
        this.isLeaf = true;
    }
    


}
