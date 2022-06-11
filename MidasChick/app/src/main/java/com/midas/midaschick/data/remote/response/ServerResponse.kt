package com.midas.midaschick.data.remote.response

import com.google.gson.annotations.SerializedName

data class ServerResponse (

    @field:SerializedName("probs")
    val probs: List<Double?>? = null,

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("run_time")
    val runTime: Double? = null,

    @field:SerializedName("pred")
    val pred: Int? = null,

    @field:SerializedName("model")
    val model: String? = null,

    @field:SerializedName("labels")
    val labels: List<String?>? = null
)