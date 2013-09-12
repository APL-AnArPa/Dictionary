rm -f ../Files/*
python generateDicLoc.py
cd ../src/

java Dictionary ../Files/Dictionary1.txt ../Files/Locate1.txt -t ../Files/TimeFileInsert_RBT.txt ../Files/TimeFileSearch_RBT.txt
java Dictionary ../Files/Dictionary2.txt ../Files/Locate2.txt -t ../Files/TimeFileInsert_RBT.txt ../Files/TimeFileSearch_RBT.txt
java Dictionary ../Files/Dictionary3.txt ../Files/Locate3.txt -t ../Files/TimeFileInsert_RBT.txt ../Files/TimeFileSearch_RBT.txt
java Dictionary ../Files/Dictionary4.txt ../Files/Locate4.txt -t ../Files/TimeFileInsert_RBT.txt ../Files/TimeFileSearch_RBT.txt
java Dictionary ../Files/Dictionary5.txt ../Files/Locate5.txt -t ../Files/TimeFileInsert_RBT.txt ../Files/TimeFileSearch_RBT.txt

java Dictionary -bst ../Files/Dictionary1.txt ../Files/Locate1.txt -t ../Files/TimeFileInsert_BST.txt ../Files/TimeFileSearch_BST.txt
java Dictionary -bst ../Files/Dictionary2.txt ../Files/Locate2.txt -t ../Files/TimeFileInsert_BST.txt ../Files/TimeFileSearch_BST.txt
java Dictionary -bst ../Files/Dictionary3.txt ../Files/Locate3.txt -t ../Files/TimeFileInsert_BST.txt ../Files/TimeFileSearch_BST.txt
java Dictionary -bst ../Files/Dictionary4.txt ../Files/Locate4.txt -t ../Files/TimeFileInsert_BST.txt ../Files/TimeFileSearch_BST.txt
java Dictionary -bst ../Files/Dictionary5.txt ../Files/Locate5.txt -t ../Files/TimeFileInsert_BST.txt ../Files/TimeFileSearch_BST.txt

