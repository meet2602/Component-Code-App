package com.materialsouk.allcodeapp.models

class ExpandModel(private var expanded: Boolean = false) {
    fun isExpanded(): Boolean {
        return expanded
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }

}