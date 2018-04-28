package com.example.msidorov.testdatabindings.domain.model

/**
 * @author m.sidorov
 */
interface Contact {

    enum class ContactType(value: Int) {
        UNKNOWN(0), EMAIL(1), PHONE(2), SKYPE(3);

        companion object {
            @JvmStatic
            fun fromOrdinal(value: Int) : ContactType{
                return when(value){
                    0 -> UNKNOWN
                    1 -> EMAIL
                    2 -> PHONE
                    3 -> SKYPE
                    else -> throw IllegalArgumentException("Invalid ordinal value [$value]");
                }
            }
        }
    }

    val id: Long;

    var value: String;

    var type: ContactType;

}