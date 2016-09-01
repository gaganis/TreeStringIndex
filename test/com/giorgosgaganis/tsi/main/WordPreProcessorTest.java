/*
    Copyright (C) 2016 Giorgos Gaganis

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Website <http://giorgosgaganis.com>
    e-mail  <gaganis@yahoo.com>
 */
package com.giorgosgaganis.tsi.main;

import com.giorgosgaganis.tsi.populator.WordPreProcessor;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordPreProcessorTest {

    @Test
    public void test_lowercase_word() {
        WordPreProcessor preProcessor = new WordPreProcessor();
        String result = preProcessor.process("γκαγκανης");
        System.out.println("result = " + result);

        assertThat(result).isEqualTo("ΓΚΑΓΚΑΝΗΣ");
    }

    @Test
    public void test_mixed_word() {
        WordPreProcessor preProcessor = new WordPreProcessor();
        String result = preProcessor.process("Γκαγκανης");
        System.out.println("result = " + result);

        assertThat(result).isEqualTo("ΓΚΑΓΚΑΝΗΣ");
    }

    @Test
    public void test_punctuated_mixed_word() {
        WordPreProcessor preProcessor = new WordPreProcessor();
        String result = preProcessor.process("Γκαγκάνης");
        System.out.println("result = " + result);

        assertThat(result).isEqualTo("ΓΚΑΓΚΑΝΗΣ");
    }

    @Test
    public void test_capitalized_word() {
        WordPreProcessor preProcessor = new WordPreProcessor();
        String result = preProcessor.process("ΓΚΑΓΚΑΝΗΣ");
        System.out.println("result = " + result);

        assertThat(result).isEqualTo("ΓΚΑΓΚΑΝΗΣ");
    }
}