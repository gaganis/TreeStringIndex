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
package com.giorgosgaganis.tsi.populator;

import com.google.common.primitives.Chars;

import java.util.HashMap;
import java.util.stream.Collectors;

public class WordPreProcessor {
    private final HashMap<Character, Character> punctuatedCharactersMap = new HashMap<>();

    public WordPreProcessor() {
        punctuatedCharactersMap.put('ί', 'ι');
        punctuatedCharactersMap.put('έ', 'ε');
        punctuatedCharactersMap.put('ά', 'α');
        punctuatedCharactersMap.put('ή', 'η');
        punctuatedCharactersMap.put('ό', 'ο');
        punctuatedCharactersMap.put('ύ', 'υ');
        punctuatedCharactersMap.put('ώ', 'ω');

        punctuatedCharactersMap.put('Ί', 'Ι');
        punctuatedCharactersMap.put('Έ', 'Ε');
        punctuatedCharactersMap.put('Ά', 'Α');
        punctuatedCharactersMap.put('Ή', 'Η');
        punctuatedCharactersMap.put('Ό', 'Ο');
        punctuatedCharactersMap.put('Ύ', 'Υ');
        punctuatedCharactersMap.put('Ώ', 'Ω');
    }

    public String process(String next) {
        return Chars.asList(next.toCharArray())
                .stream()
                .map(character -> punctuatedCharactersMap.get(character) == null ? character : punctuatedCharactersMap.get(character))
                .map(Character::toUpperCase)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
