package com.techlads.composetv.features.home.carousel

const val SKIP_TAG = "Skip"
const val HERO_ITEM_TAG = "hero_item"
const val SECTIONS_LIST_TAG = "sections_list"
const val SECTION_ITEM_TAG = "section_item_{parent}_{child}"
const val PRODUCT_DETAIL_BANNER_TAG = "product_detail_banner"

fun tagForItem(parent: Int, child: Int) = SECTION_ITEM_TAG.replace("{parent}", parent.toString()).replace("{child}", child.toString())