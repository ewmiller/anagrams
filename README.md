#Anagrams Finder

The goal of this program is to find all anagrams in a dictionary. Given a text
file consisting of lowercase words, this program computes all anagram "classes"
that occur in the dictionary. For example, the words "tops", "opts", and "pots"
are all in the same anagram class.

The result of a successful run is a text file containing a list of every word,
organized by anagram class, where each line in the file is a separate class.

---

## Usage

Provided in the directory are two bash scripts, `build.sh` and `run.sh`.
The build script is used for compilation. The run script will prompt for user
input and then execute the program. To read file `dict1`, simply type `dict1`
when asked (likewise for `dict2`). Screen output will be shown during runtime
to track progress of the program. When done, the file `anagram1` or `anagram2`
will become available in the directory.

## Algorithm

The general algorithm for this program is as follows:

1. Read dictionary file in as an an ArrayList A of words.
2. Create an array B such that B.length == A.length
3. Each entry in B is a list, representing an anagram class.
4. Iterate through every word in A. For each word, iterate through the non-empty
elements of B to see if the word matches any of the equivalence classes. If yes,
add the word to that class (append to list). If the word matches no existing
classes (iteration hits an empty row in B), add that word to a new equivalence
class in B.
5. Call a print function on B to write each row to a line in a text file.

## Run Time

In the worst case, every word in the given dictionary is part of its own anagram
class (none are equivalent). Thus the algorithm would have to iterate through
the array of anagram classes completely, on every word. (Actual runtime analysis
TBD).
