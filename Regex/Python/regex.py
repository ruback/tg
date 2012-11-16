# -*- coding: utf-8 -*-
import re, time

string_list = []
regex_list = []

repetitions = []
tests = 5

def add_regex(str, regex, reps):
	string_list.append(str);
	regex_list.append(re.compile(regex));
	repetitions.append(reps);

def load_regex():
	warm_up_string_1 = "Apenas um teste para \"Aquecer\" o software"
	warm_up_regex_1 = "software"
	warm_up_reps = 1000000
	add_regex(warm_up_string_1, warm_up_regex_1, warm_up_reps)
		
	#Common sense
		
	non_capturing_parentesis_string_1 = "E eu estava dormindo: ZzZzZzZzZzZzZz"
	non_capturing_parentesis_regex_1 = "(Zz)*"
	non_capturing_parentesis_regex_2 = "(?:Zz)*"
	non_capturing_parentesis_reps = 10000000
	add_regex(non_capturing_parentesis_string_1, non_capturing_parentesis_regex_1, non_capturing_parentesis_reps)
	add_regex(non_capturing_parentesis_string_1, non_capturing_parentesis_regex_2, non_capturing_parentesis_reps)
	
	superfluous_parentesis_string_1 = 'Meu login é thiago89 e tenho 22 anos.'
	superfluous_parentesis_regex_1 = "é (?:.)* e"
	superfluous_parentesis_regex_2 = "é .* e"
	superfluous_parentesis_reps = 100000
	add_regex(superfluous_parentesis_string_1, superfluous_parentesis_regex_1, superfluous_parentesis_reps)
	add_regex(superfluous_parentesis_string_1, superfluous_parentesis_regex_2, superfluous_parentesis_reps)
	
	superfluous_classes_string_1 = 'Meu login é thiago89 e tenho 22 anos.'
	superfluous_classes_regex_1 = "89 [e] tenho [2]* anos"
	superfluous_classes_regex_2 = "89 e tenho 2* anos"
	superfluous_classes_reps = 1000000
	add_regex(superfluous_classes_string_1, superfluous_classes_regex_1, superfluous_classes_reps)
	add_regex(superfluous_classes_string_1, superfluous_classes_regex_2, superfluous_classes_reps)
	
	leading_anchors_string_1 = "Some huge string that will never match the regex"
	leading_anchors_regex_1 = ".*Hello"
	leading_anchors_regex_2 = "^.*Hello"
	leading_anchors_reps = 10000
	add_regex(leading_anchors_string_1, leading_anchors_regex_1, leading_anchors_reps)
	add_regex(leading_anchors_string_1, leading_anchors_regex_2, leading_anchors_reps)
			
	# Expose Literal text
	
	required_quatifiers_string_1 = "aa aaa a aaa a aaaa aaa bbb a bbbb aaa bbb a ccc eee aaaa eee ccc aaaaaaaaa"
	required_quatifiers_regex_1 = "a{7,10}"
	required_quatifiers_regex_2 = "aaaaaaaa{0,3}"
	required_quatifiers_reps = 100000
	add_regex(required_quatifiers_string_1, required_quatifiers_regex_1, required_quatifiers_reps)
	add_regex(required_quatifiers_string_1, required_quatifiers_regex_2, required_quatifiers_reps)
	
	required_alternation_string_1 = "those who don't find this interesting are the ones that I shouldn't be sending there"
	required_alternation_regex_1 = "(?:there|they)"
	required_alternation_regex_2 = "the(?:re|y)"
	required_alternation_reps = 100000
	add_regex(required_alternation_string_1, required_alternation_regex_1, required_alternation_reps)
	add_regex(required_alternation_string_1, required_alternation_regex_2, required_alternation_reps)
	
	# Expose Anchors
	
	expose_front_string_1 = "not here \nnot here \nnot here \nnot here \nnot here \nnot here \nnot here \nstart"
	expose_front_regex_1 = "^start|^begin"
	expose_front_regex_2 = "^(?:begin|start)"
	expose_front_reps = 100000
	add_regex(expose_front_string_1, expose_front_regex_1, expose_front_reps)
	add_regex(expose_front_string_1, expose_front_regex_2, expose_front_reps)
	
	expose_back_string_1 = "not here \nnot here \nnot here \nnot here \nnot here \nnot here \nnot here \n it's here finish\n not here"
	expose_back_regex_1 = "end$|finish$"
	expose_back_regex_2 = "(?:end|finish)$"
	expose_back_reps = 100000
	add_regex(expose_back_string_1, expose_back_regex_1, expose_back_reps)
	add_regex(expose_back_string_1, expose_back_regex_2, expose_back_reps)
	
	# Lazy vs Greedy
	
	lazy_string_1 = "in the begining a bunch of text and more text and more text and more text and more text and more text and more text and more text and more text "
	lazy_regex_1 = ".*begining"
	lazy_regex_2 = ".*?begining"
	lazy_reps = 100000
	add_regex(lazy_string_1, lazy_regex_1, lazy_reps)
	add_regex(lazy_string_1, lazy_regex_2, lazy_reps)
	
	greedy_string_1 = "a bunch of text and more text and more text and more text and more text and more text and more text and more text and more text in the end"
	greedy_regex_1 = ".*end"
	greedy_regex_2 = ".*?end"
	greedy_reps = 100000
	add_regex(greedy_string_1, greedy_regex_1, greedy_reps)
	add_regex(greedy_string_1, greedy_regex_2, greedy_reps)
	
	# One vs Multiple
	
	one_vs_multiple_string_1 = "a bunch of text and more text and more text rr and more text and more text and more text and more text and more text and more text and a test the end"
	one_vs_multiple_regex_1 = "test|monkey|andy"
	one_vs_multiple_regex_2 = "test"
	one_vs_multiple_regex_3 = "monkey"
	one_vs_multiple_regex_4 = "andy"
	one_vs_multiple_reps = 100000
	add_regex(one_vs_multiple_string_1, one_vs_multiple_regex_1, one_vs_multiple_reps)
	add_regex(one_vs_multiple_string_1, one_vs_multiple_regex_2, one_vs_multiple_reps)
	add_regex(one_vs_multiple_string_1, one_vs_multiple_regex_3, one_vs_multiple_reps)
	add_regex(one_vs_multiple_string_1, one_vs_multiple_regex_4, one_vs_multiple_reps)
	
	# Mimic Initial-Character Discrimination
	
	mimic_initial_string_1 = "a bunch of yell and readable text and more a bunch of yell and readable text and more a bunch of yell and readable text and more a bunch of yell and readable text and more blue"
	mimic_initial_regex_1 = "blue|yellow|red"
	mimic_initial_regex_2 = "(?=[byr])(?:blue|yellow|red)"
	mimic_initial_reps = 100000
	add_regex(mimic_initial_string_1, mimic_initial_regex_1, mimic_initial_reps)
	add_regex(mimic_initial_string_1, mimic_initial_regex_2, mimic_initial_reps)
	
	# Atomic Grouping and Possesive Quantifiers
	
	#possesive_string_1 = "thiago:ruback:elvis:henrique:fabio:mokarzel:walter:filho:john:smith:seu:madruga:barack:obama"
	#possesive_regex_1 = "[^:]+:obama"
	#possesive_regex_2 = "[^:]++:obama"
	#possesive_regex_3 = "(?>[^:]+):obama"
	#possesive_reps = 10000
	#add_regex(possesive_string_1, possesive_regex_1, possesive_reps)
	#add_regex(possesive_string_1, possesive_regex_2, possesive_reps)
	#add_regex(possesive_string_1, possesive_regex_3, possesive_reps)
	
	# Lead to a match
	
	most_comom_first_string_1 = "foot"
	most_comom_first_regex_1 = "(?:andy|barbie|car|dice|ex|foot)"
	most_comom_first_regex_2 = "(?:foot|andy|barbie|car|dice|ex)"
	most_comom_first_reps = 1000000
	add_regex(most_comom_first_string_1, most_comom_first_regex_1, most_comom_first_reps)
	add_regex(most_comom_first_string_1, most_comom_first_regex_2, most_comom_first_reps)
	
	distribute_end_string_1 = "I want to find the last one but the is a lot of exexexexexexexexexexexexexexexexexexexexexexexexexexexe in my way here"
	distribute_end_regex_1 = "(?:ex|some|other|excelent|stuff|way)\\b"
	distribute_end_regex_2 = "(?:ex\\b|some\\b|other\\b|excelent\\b|stuff\\b|way\\b)"
	distribute_end_reps = 10000
	add_regex(distribute_end_string_1, distribute_end_regex_1, distribute_end_reps)
	add_regex(distribute_end_string_1, distribute_end_regex_2, distribute_end_reps)
	
	# Use what you have!
	
	digits_string_1 = "I want to find 555 a number 888 in this regex lower with only digit lowe than four and here it is 422"
	digits_regex_1 = "(?:1|2|3|4)+"
	digits_regex_2 = "[1-4]+"
	digits_reps = 100000
	add_regex(digits_string_1, digits_regex_1, digits_reps)
	add_regex(digits_string_1, digits_regex_2, digits_reps)

def run_regex():
  for i, string in enumerate(string_list):
		#regex stuff
		regex = regex_list[i]
		deltas = []
		mean = 0
		
		#warm up
		for k in range(repetitions[i]):
			regex.search(string)
		
		for j in range(tests):
			begin = time.clock()
			for k in range(repetitions[i]):
				regex.search(string)
			end = time.clock()
			deltas.append(end - begin)
			mean += (end - begin)
		mean /= tests
		
		found = (regex.search(string) != None)
		
		#print stuff
		print "String : " + string[:40]
		print "Regex : " + regex.pattern
		for j, delta in enumerate(deltas):
			print "Test " + str(j) + ": " + str(delta)
		print "Media:  " + str(1000*mean)
		print "Achou: " + str(found)
		print ""

	
def main():
	load_regex()
	run_regex()

if __name__ == "__main__":
  main()