# MeliProject
	Meli project was created to help Magneto find the mutants and rule the world.
	To achieve this there are two services
	
	** POST https://meli-198523.appspot.com/mutants/mutant **
	This service read the dna in the body of the request to identify if it is mutant.
	The body must be a json e.g.
	```
	{
		“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
	}
	```
	If dna is mutant true will be return, else it will return false
	If the dna is not valid a 400 error status code will be return.
	
	** GET https://meli-198523.appspot.com/mutants/stats **
	This service return the number of humans, mutants and the ratio in a json e.g.
	```
	{
		“count_mutant_dna”:40, 
		“count_human_dna”:100: 
		“ratio”:0.4
	}
	```
	