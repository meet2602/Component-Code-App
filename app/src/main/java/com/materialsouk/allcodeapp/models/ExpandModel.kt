package com.materialsouk.allcodeapp.models


class ExpandModel(private var name:String,private var expanded:Boolean = false) {

    fun setName(name: String) {
        this.name = name
    }
    fun getName():String{
        return name
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }
    fun getExpanded():Boolean{
        return expanded
    }
}