package ch.zindustries.dmzapp

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/document")
class DocumentController(
    private val documentRepository: DocumentRepository,
) {

    @GetMapping("/{id}")
    fun getDoc(@PathVariable id: Long): Document? {
        return documentRepository.findById(id).orElse(Document())
    }

    @GetMapping
    fun getDocs(): List<Document?> {
        return documentRepository.findAll()
    }

    @PostMapping
    fun saveDocument(@RequestBody document: DocumentDTO): Document? {
        val doc = Document()
        doc.name = document.name
        doc.size = document.size
        return documentRepository.save(doc)
    }
}
