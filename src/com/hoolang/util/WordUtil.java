package com.hoolang.util;

import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

public class WordUtil {
	public static String split(String word){
		List<Word> words = WordSegmenter.segWithStopWords(word);
		return words.toString().replace("[", "").replace("]", "");
	}
}
