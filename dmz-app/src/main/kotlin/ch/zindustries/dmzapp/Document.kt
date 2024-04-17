package ch.zindustries.dmzapp

import jakarta.persistence.*

@Entity
@Table(name = "document")
class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column
    var size: Int? = null
}
