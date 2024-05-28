package com.example.gourmet_therecepieapp

/*A sealed class is a class that can only be subclassed
within the same file in which it is defined.This means that
 all subclasses of a sealed class are known at compile time.
Sealed classes are useful for representing a fixed set of
possible states or types.
For example, you could use a sealed class to represent the
different states of a network request:

 */
sealed class Screen( val route: String){
    object ReceipeScreen: Screen("receipescreen")
    object DetailScreen: Screen("detailscreen")

}