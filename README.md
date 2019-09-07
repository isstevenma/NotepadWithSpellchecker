Notepad With Spell Checker

Notepad-esque application with built-in spell checker. 

Users:
Please download the application here [link](https://drive.google.com/file/d/1Gbv3VpEs-wDOPmEMtckjAy9OP8DakLgC/view) and extract to a directory
Open the .jar file to open the application

Prerequisites:
This application requires Java Standard Edition Runtime 8

About the project:

The project began in March 2019, and was largely inspired by a presentation I attended for aspiring co-op students. 

I had a rough structure of the project in my head, knowing that I would most likely need to use a tree data data structure, and some sort of recursive algorithim to both search through the tree,
and differentiate between unique strings. Through my research, I found two tree structures that would work for a spell checker, A BK Tree structure and a Trie structure; I ultimately decicided
to use a BK structure because I had a better understanding of how it functioned based on what I read and watched, and more people seemed to recommend it over the Trie structure for spell checker programs.
The base structure of the BK tree was relatively easy to code, and its design was based on my understanding of how a BK tree functioned as explained through the web, which essentially revolved around nodes and the usage of hashmaps. 
The search function was a little trickier to code, with the first couple drafts of the methods being convoluted versions of the final one. While functional, the search function was far too slow to be usable. I eventually 
went over the search function that i had coded and found out that I was keeping track of alot of data that wasn't neccessary, and was even somewhat violating some OOP principles. By cleaning up the code, the search function became far 
more efficient for run-time use.

Levenshtein distance was also something I discovered as a result of researching the BK tree. At face-value the concept seemed pretty easy to code, but I was unfortunately mistakened. Outside of coding the UI, coding the method that calculated
the levenshtein distance between two strings was arguably the most arduous process for the project. I ultimately had a very large, and frank convoluted, recursive function that functioned properly for the most part, outside of a few border cases. 
I ultimately resorted to researching about more effective ways to coding this method, which is where I discovered dynamic programming, which made the whole process quite easy in hindsight. 

UI was also another portion of the project that was quite exhausting the code, not because it was difficult per-say, but because SWING was not something that I was very comfortable with at the time.
Surprisingly, the most frusturating portion was getting the highlighter to work properly, mainly due to its weird functionality in correspondance with SWING's JTextArea. 

Developers:

The project was developed with Intellij IDEA 2017.3.5[Community Edition]

