package com.example.mst3;

import org.junit.Test;

public class ModelTest
{
    @Test(expected = IllegalArgumentException.class)
    public void alreadyExistsCityTest()
    {
       Model model = new Model();
       model.addPoint(20, 20, "san miguel", "buenos aires");
       model.addPoint(20, 20, "san miguel", "buenos aires");
    }


}
