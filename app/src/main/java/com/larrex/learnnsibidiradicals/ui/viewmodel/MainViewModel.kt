package com.larrex.learnnsibidiradicals.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.larrex.learnnsibidiradicals.R
import com.larrex.learnnsibidiradicals.model.DrawLine
import com.larrex.learnnsibidiradicals.model.NsibidiItemModel

class MainViewModel : ViewModel() {

    var selectedColor = mutableStateOf(0)
    val drawLineList = mutableStateListOf<DrawLine>()
    val nsibidilist = listOf<NsibidiItemModel>(
        NsibidiItemModel(R.drawable.nsibidi_road, "okporo uzo"),
        NsibidiItemModel(R.drawable.nsibidi_ihunaya, "ihunaya/ifunanya"),
        NsibidiItemModel(R.drawable.nsibidi_unity, "yị"),
        NsibidiItemModel(R.drawable.nsibidi_talk, "ókwú"),
        NsibidiItemModel(R.drawable.nsibidi_raise, "zụ"),
        NsibidiItemModel(R.drawable.nsibidi_leopard, "agụ"),
        NsibidiItemModel(R.drawable.nsibidi_leaf, "ákwụ́kwọ́"),
        NsibidiItemModel(R.drawable.nsibidi_water, "mmiri"),
        NsibidiItemModel(R.drawable.nsibidi_person, "mádu"),
        NsibidiItemModel(R.drawable.nsibidi_us, "anyi"),
        NsibidiItemModel(R.drawable.nsibidi_spider, "ududọ"),
        NsibidiItemModel(R.drawable.nsibidi_python, "agwo"),
        NsibidiItemModel(R.drawable.nsibidi_child, "nwa"),
        NsibidiItemModel(R.drawable.nsibidi_woman, "nwanyi"),
        NsibidiItemModel(R.drawable.nsibidi_man, "oke"),
        NsibidiItemModel(R.drawable.nsibidi_protitute, "akwuna"),
        NsibidiItemModel(R.drawable.nsibidi_music, "égwú"),
        NsibidiItemModel(R.drawable.nsibidi_place, "ébé"),
        NsibidiItemModel(R.drawable.nsibidi_money, "okpogho/ikpeghe"),
        NsibidiItemModel(R.drawable.nsibidi_drink, "ṅụọe"),
        NsibidiItemModel(R.drawable.nsibidi_tree, "ojukwu"),
        NsibidiItemModel(R.drawable.nsibidi_night, "ábàlì"),
        NsibidiItemModel(R.drawable.nsibidi_come, "bia"),
        NsibidiItemModel(R.drawable.nsibidi_slave, "oru/ohu"),
        NsibidiItemModel(R.drawable.nsibidi_eye, "ányá"),
        NsibidiItemModel(R.drawable.nsibidi_sky, "igwe"),
        NsibidiItemModel(R.drawable.nsibidi_civilisation, "ife"),
        NsibidiItemModel(R.drawable.nsibidi_day, "ubọchi"),
        NsibidiItemModel(R.drawable.nsibidi_follow, "só"),
        NsibidiItemModel(R.drawable.nsibidi_shine, "chá"),
        NsibidiItemModel(R.drawable.nsibidi_shine, "ígbà"),
        NsibidiItemModel(R.drawable.nsibidi_shine, "njọ"),
        NsibidiItemModel(R.drawable.nsibidi_shine, "zo"),
        NsibidiItemModel(R.drawable.nsibidi_energy, "íké"),
        NsibidiItemModel(R.drawable.nsibidi_money2, "égó"),
        NsibidiItemModel(R.drawable.nsibidi_smoke, "anwùrù"),
        NsibidiItemModel(R.drawable.nsibidi_said, "sị"),
        NsibidiItemModel(R.drawable.nsibidi_stream, "íyí"),
        NsibidiItemModel(R.drawable.nsibidi_fruit, "mkpụlụ"),
        NsibidiItemModel(R.drawable.nsibidi_kitchen, "ekwu"),
        NsibidiItemModel(R.drawable.nsibidi_blessing, "gọzi"),
    )

    val nsibidiSearchList = mutableListOf<NsibidiItemModel>()

    fun doSearch(keyword: String) {

        nsibidiSearchList.clear()

        nsibidilist.forEach{nsibidi->

            if (nsibidi.word.contains(keyword)){
                nsibidiSearchList.add(nsibidi)
            }

        }


    }

}