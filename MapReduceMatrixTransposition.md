# MapReduce exercice

## Pseudo-code of a MapReduce algorithm which takes a matrix as input and outputs the transposed matrix. 

```python
map(k, v){
    #k: the key which will represent here the number of the corresponding line of the input matrix
    #v: the corresponding value to the key which will be the line corresponding to the line n°k, 
    #a list of comma separated values
    
    l = [] #empty list which will be the output of the map function 
    
    for(i in range(len(v)){
      #we add to the list l the element v[i] of the line with its corresponding position i
      #in the line k, i.e the column
      #index of the elment v[i] in the entire matrix given as input.
      l = l + [[i,v[i]]]
    }
    
    #return type is list(key2,value2) whereas the input was (key1,value1)
    return(l) 
}
```

#Then, the values with the same key are shuffled, sorted and gathered according to the key values.
#It means that the result is the key (which is the column index matrix) with all the values (elements of the matrix) 
#which have the same key (i.e the same column index) 
#Consequently, we obtain each column index with the elements in this column: the output is the transposed matrix. 

```python
reduce(k2, list(v2)){
    
    #the reduce function is the identity, we do not need the reducers in this particular case
    return (k2,list(v2))
}
```
