package ashwini.dev

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class HelloWorldApiTest {

    @Test
    fun `GET hello-world-v1 returns correct response`() = testApplication {
        application {
            module()
        }

        client.get("/hello-world/v1").let { response ->
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals("hello-world", response.bodyAsText())
        }
    }

    @Test
    fun `GET hello-world-v1 returns correct content type`() = testApplication {
        application {
            module()
        }

        client.get("/hello-world/v1").let { response ->
            assertEquals(
                ContentType.Text.Plain.withCharset(Charsets.UTF_8).toString(),
                response.headers[HttpHeaders.ContentType]
            )
        }
    }

    @Test
    fun `GET non-existent endpoint returns 404`() = testApplication {
        application {
            module()
        }

        client.get("/non-existent").let { response ->
            assertEquals(HttpStatusCode.NotFound, response.status)
        }
    }

    @Test
    fun `GET hello-world-v1 with wrong method returns 405`() = testApplication {
        application {
            module()
        }

        client.post("/hello-world/v1").let { response ->
            assertEquals(HttpStatusCode.MethodNotAllowed, response.status)
        }
    }
}
