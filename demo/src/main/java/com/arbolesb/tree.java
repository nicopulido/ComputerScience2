package com.arbolesb;

import com.arbolesb.key;
import java.util.Arrays;

public class tree implements leafOperations {
    public leaf fatherLeaf;
    public int grade;
    public int indexNodeFather = 0;

    @Override
    public void insertion(key valueKey) {
        
        if (fatherLeaf == null) {
            fatherLeaf = new leaf(grade);
            fatherLeaf.keys[0] = valueKey;
            fatherLeaf.numKeys++;
        } else {
            if (fatherLeaf.numKeys < fatherLeaf.maxKeys) {
                fatherLeaf.keys[fatherLeaf.numKeys] = valueKey;
                fatherLeaf.numKeys++;
                Arrays.sort(fatherLeaf.keys);

            } else {
                indexNodeFather = (int) Math.floor(grade/2);
                if (fatherLeaf.keys != null) {
                    for (int i = indexNodeFather; i >= 0; i--) { 
                        fatherLeaf.childrenLeafs[i] = fatherLeaf.childrenLeafs[i];
                        Arrays.sort(fatherLeaf.childrenLeafs[i].keys);
                    }
                    for (int i = indexNodeFather + 1; i < grade; i++) { 
                        fatherLeaf.childrenLeafs[i] = fatherLeaf.childrenLeafs[i];
                        Arrays.sort(fatherLeaf.childrenLeafs[i].keys);
                    }
                } else {
                   
                }
                
                
            }
        }
        

    }

    @Override
    public void deletion(key valuKey) {
        // TODO: Implement deletion logic
    }
}
