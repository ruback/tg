import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RunRegex {
	ArrayList<Pattern> regex_list = new ArrayList<Pattern>();
	ArrayList<String> string_list = new ArrayList<String>();
	ArrayList<Integer> repetitions_list = new ArrayList<Integer>();
	int tests = 5;
	
	public RunRegex() {
		loadRegex();
		runRegex();
	}
	
	private void runRegex() {
		for (int i = 0; i < string_list.size(); ++i) {
			//regex stuff
			String string = string_list.get(i);
			Pattern regex = regex_list.get(i);
			ArrayList<Double> deltas = new ArrayList<Double>();
			double mean = 0;
			double begin, end;
			Matcher matcher = regex.matcher(string);
			
			boolean found = matcher.find();
			
			//Warm up run:
			for (int k = 0; k < repetitions_list.get(i); ++k) {
				matcher.find();
				matcher.reset();
			}
			
			for (int j = 0; j < tests; ++j) {
				begin = System.nanoTime(); 
				for (int k = 0; k < repetitions_list.get(i); ++k) {
					matcher.find();
					matcher.reset();
				}
				end = System.nanoTime(); 
				deltas.add(((end - begin)/1000000000));
				mean += ((end - begin)/1000000000);
			}
			mean /= tests;
			
			//print stuff
			System.out.println("String : " + string.replaceAll("\n", "\\\\n").substring(0,Math.min(40, string.length())));
			System.out.println("Regex : " + regex.pattern());
			for (int j = 0; j < deltas.size(); ++j)
				System.out.println("Test " + j + ": " + deltas.get(j));
			System.out.println("Media:  " + 1000*mean);
			System.out.println("Achou: " + found);
			System.out.println("");
		}
	}
	
	private void loadRegex(){
		String warm_up_string_1 = "Apenas um teste para \"Aquecer\" o software";
		String warm_up_regex_1 = "software";
		int warm_up_reps = 1000000;
		addRegex(warm_up_string_1, warm_up_regex_1, warm_up_reps);
		
		//Common sense
		
		String non_capturing_parentesis_string_1 = "E eu estava dormindo: ZzZzZzZzZzZzZz";
		String non_capturing_parentesis_regex_1 = "(Zz)*";
		String non_capturing_parentesis_regex_2 = "(?:Zz)*";
		int non_capturing_parentesis_reps = 10000000;
		addRegex(non_capturing_parentesis_string_1, non_capturing_parentesis_regex_1, non_capturing_parentesis_reps);
		addRegex(non_capturing_parentesis_string_1, non_capturing_parentesis_regex_2, non_capturing_parentesis_reps);
		
		String superfluous_parentesis_string_1 = "Meu login é thiago89 e tenho 22 anos.";
		String superfluous_parentesis_regex_1 = "é (?:.)* e";
		String superfluous_parentesis_regex_2 = "é .* e";
		int superfluous_parentesis_reps = 100000;
		addRegex(superfluous_parentesis_string_1, superfluous_parentesis_regex_1, superfluous_parentesis_reps);
		addRegex(superfluous_parentesis_string_1, superfluous_parentesis_regex_2, superfluous_parentesis_reps);
		//
		String superfluous_classes_string_1 = "Meu login é thiago89 e tenho 22 anos.";
		String superfluous_classes_regex_1 = "89 [e] tenho [2]* anos";
		String superfluous_classes_regex_2 = "89 e tenho 2* anos";
		int superfluous_classes_reps = 1000000;
		addRegex(superfluous_classes_string_1, superfluous_classes_regex_1, superfluous_classes_reps);
		addRegex(superfluous_classes_string_1, superfluous_classes_regex_2, superfluous_classes_reps);
		
		String leading_anchors_string_1 = "Some huge string that will never match the regex";
		String leading_anchors_regex_1 = ".*Hello";
		String leading_anchors_regex_2 = "^.*Hello";
		int leading_anchors_reps = 10000;
		addRegex(leading_anchors_string_1, leading_anchors_regex_1, leading_anchors_reps);
		addRegex(leading_anchors_string_1, leading_anchors_regex_2, leading_anchors_reps);
				
		// Expose Literal text
		
		String required_quatifiers_string_1 = "aa aaa a aaa a aaaa aaa bbb a bbbb aaa bbb a ccc eee aaaa eee ccc aaaaaaaaa";
		String required_quatifiers_regex_1 = "a{7,10}";
		String required_quatifiers_regex_2 = "aaaaaaaa{0,3}";
		int required_quatifiers_reps = 100000;
		addRegex(required_quatifiers_string_1, required_quatifiers_regex_1, required_quatifiers_reps);
		addRegex(required_quatifiers_string_1, required_quatifiers_regex_2, required_quatifiers_reps);
		
		String required_alternation_string_1 = "those who don't find this interesting are the ones that I shouldn't be sending there";
		String required_alternation_regex_1 = "(?:there|they)";
		String required_alternation_regex_2 = "the(?:re|y)";
		int required_alternation_reps = 100000;
		addRegex(required_alternation_string_1, required_alternation_regex_1, required_alternation_reps);
		addRegex(required_alternation_string_1, required_alternation_regex_2, required_alternation_reps);
		
		// Expose Anchors
		
		String expose_front_string_1 = "not here \nnot here \nnot here \nnot here \nnot here \nnot here \nnot here \nstart";
		String expose_front_regex_1 = "^begin|^start";
		String expose_front_regex_2 = "^(?:begin|start)";
		int expose_front_reps = 100000;
		addRegex(expose_front_string_1, expose_front_regex_1, expose_front_reps);
		addRegex(expose_front_string_1, expose_front_regex_2, expose_front_reps);
		
		String expose_back_string_1 = "not here \nnot here \nnot here \nnot here \nnot here \nnot here \nnot here \n it's here finish\n not here";
		String expose_back_regex_1 = "end$|finish$";
		String expose_back_regex_2 = "(?:end|finish)$";
		int expose_back_reps = 100000;
		addRegex(expose_back_string_1, expose_back_regex_1, expose_back_reps);
		addRegex(expose_back_string_1, expose_back_regex_2, expose_back_reps);
		
		// Lazy vs Greedy
		
		String lazy_string_1 = "in the begining a bunch of text and more text and more text and more text and more text and more text and more text and more text and more text ";
		String lazy_regex_1 = ".*begining";
		String lazy_regex_2 = ".*?begining";
		int lazy_reps = 100000;
		addRegex(lazy_string_1, lazy_regex_1, lazy_reps);
		addRegex(lazy_string_1, lazy_regex_2, lazy_reps);
		
		String greedy_string_1 = "a bunch of text and more text and more text and more text and more text and more text and more text and more text and more text in the end";
		String greedy_regex_1 = ".*end";
		String greedy_regex_2 = ".*?end";
		int greedy_reps = 100000;
		addRegex(greedy_string_1, greedy_regex_1, greedy_reps);
		addRegex(greedy_string_1, greedy_regex_2, greedy_reps);
		
		// One vs Multiple
		
		String one_vs_multiple_string_1 = "a bunch of text and more text and more text rr and more text and more text and more text and more text and more text and more text and a test the end";
		String one_vs_multiple_regex_1 = "test|monkey|andy";
		String one_vs_multiple_regex_2 = "test";
		String one_vs_multiple_regex_3 = "monkey";
		String one_vs_multiple_regex_4 = "andy";
		int one_vs_multiple_reps = 100000;
		addRegex(one_vs_multiple_string_1, one_vs_multiple_regex_1, one_vs_multiple_reps);
		addRegex(one_vs_multiple_string_1, one_vs_multiple_regex_2, one_vs_multiple_reps);
		addRegex(one_vs_multiple_string_1, one_vs_multiple_regex_3, one_vs_multiple_reps);
		addRegex(one_vs_multiple_string_1, one_vs_multiple_regex_4, one_vs_multiple_reps);
		
		// Mimic Initial-Character Discrimination
		
		String mimic_initial_string_1 = "a bunch of yell and readable text and more a bunch of yell and readable text and more a bunch of yell and readable text and more a bunch of yell and readable text and more blue";
		String mimic_initial_regex_1 = "blue|yellow|red";
		String mimic_initial_regex_2 = "(?=[byr])(?:blue|yellow|red)";
		int mimic_initial_reps = 100000;
		addRegex(mimic_initial_string_1, mimic_initial_regex_1, mimic_initial_reps);
		addRegex(mimic_initial_string_1, mimic_initial_regex_2, mimic_initial_reps);
		
		// Atomic Grouping and Possesive Quantifiers
		
		//String possesive_string_1 = "thiago:ruback:elvis:henrique:fabio:mokarzel:walter:filho:john:smith:seu:madruga:barack:obama";
		//String possesive_regex_1 = "[^:]+:obama";
		//String possesive_regex_2 = "[^:]++:obama";
		//String possesive_regex_3 = "(?>[^:]+):obama";
		String possesive_string_1 = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
		String possesive_regex_1 = "\\w+[^\\w]";
		String possesive_regex_2 = "\\w++[^\\w]";
		String possesive_regex_3 = "(?>[\\w]+)[^\\w]";
		int possesive_reps = 10000;
		addRegex(possesive_string_1, possesive_regex_1, possesive_reps);
		addRegex(possesive_string_1, possesive_regex_2, possesive_reps);
		addRegex(possesive_string_1, possesive_regex_3, possesive_reps);
		
		// Lead to a match
		
		String most_comom_first_string_1 = "foot";
		String most_comom_first_regex_1 = "(?:andy|barbie|car|dice|ex|foot)";
		String most_comom_first_regex_2 = "(?:foot|andy|barbie|car|dice|ex)";
		int most_comom_first_reps = 1000000;
		addRegex(most_comom_first_string_1, most_comom_first_regex_1, most_comom_first_reps);
		addRegex(most_comom_first_string_1, most_comom_first_regex_2, most_comom_first_reps);
		
		String distribute_end_string_1 = "I want to find the last one but the is a lot of exexexexexexexexexexexexexexexexexexexexexexexexexexexe in my way here";
		String distribute_end_regex_1 = "(?:ex|some|other|excelent|stuff|way)\\b";
		String distribute_end_regex_2 = "(?:ex\\b|some\\b|other\\b|excelent\\b|stuff\\b|way\\b)";
		int distribute_end_reps = 10000;
		addRegex(distribute_end_string_1, distribute_end_regex_1, distribute_end_reps);
		addRegex(distribute_end_string_1, distribute_end_regex_2, distribute_end_reps);
		
		// Use what you have!
		
		String digits_string_1 = "I want to find 555 a number 888 in this regex lower with only digit lowe than four and here it is 422";
		String digits_regex_1 = "(?:1|2|3|4)+";
		String digits_regex_2 = "[1-4]+";
		int digits_reps = 100000;
		addRegex(digits_string_1, digits_regex_1, digits_reps);
		addRegex(digits_string_1, digits_regex_2, digits_reps);
	}
	
	private void addRegex(String str, String regex, int reps){
		string_list.add(str);
		regex_list.add(Pattern.compile(regex, Pattern.MULTILINE));
		repetitions_list.add(reps);
	}
}
