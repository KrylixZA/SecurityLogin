/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Random;

/**
 *
 * @author Simon
 */
public class OneTimePin {

    public OneTimePin() {
    }

    public final String generateOneTimePin() {
        Random random = new Random();
        int n = 10 + random.nextInt() * 60;
        while (n < 0) {
            n = 10 + random.nextInt() * 60;
        }

        return Integer.toString(n);
    }

}
