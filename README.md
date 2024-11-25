# HTTP Protocol
## HTTP Overview
* HTTP is a protocol for fetching resources such as HTML documents.it is a client-server protocol, which means requests are initiated by the recipient, usually the Web browser.
* Clients and servers communicate by exchanging individual messages (as opposed to a stream of data). The messages sent by the client are called requests and the messages sent by the server as an answer are called responses.
*  It is an application layer protocol that is sent over TCP, or over a TLS-encrypted TCP connection, though any reliable transport protocol could theoretically be used.

![image](https://github.com/user-attachments/assets/efda2dba-3bdb-47e6-acdf-fab7ba84c2a5)

## Components of HTTP-based systems
Each individual request is sent to a server, which handles it and provides an answer called the response. Between the client and the server there are numerous entities, collectively called proxies.

#### 1-Client: the user-agent
* A user-agent, typically a web browser, initiates requests to fetch web pages. It parses the HTML document, loads additional resources like CSS and images, and executes scripts to render the complete page.
* Web pages are hypertext documents, meaning they contain links that, when clicked, trigger new requests to the server. The browser then processes these requests and updates the displayed page accordingly. This process allows users to navigate the web by following links and interacting with web content.

#### 2-Proxies
When a web browser and a server communicate, their messages travel through a network of computers and machines. Most of these intermediary devices operate at lower network layers (transport, network, physical) and are typically transparent, meaning they don't affect the HTTP messages. However, some devices, known as proxies, operate at the application layer and can modify or intercept these messages.

Proxies can perform various functions:

* Caching: Storing copies of frequently accessed content to reduce server load and improve response times.
* Filtering: Blocking or modifying content based on specific criteria, such as security or parental control policies.
* Load Balancing: Distributing incoming traffic across multiple servers to improve performance and reliability.
* Authentication: Controlling access to resources by verifying user credentials.
*Logging: Recording information about network traffic for analysis and security purposes.

A proxy can be on the user's local computer, or anywhere between the user's computer and a destination server on the Internet. In general there are two main types of proxy servers:

* A forward proxy that handles requests from and to anywhere on the Internet.
* A reverse proxy taking requests from the Internet and forwarding them to servers in an internal network.

#### 3- Servers
A server is responsible for providing the requested resources to the client. While it may appear as a single machine, it can be a cluster of servers working together to handle the load.

These servers can collaborate to generate content dynamically, using databases and other software components. Modern protocols like `HTTP/1.1` allow multiple server instances to share a single IP address acrros  the `Host` header, enhancing efficiency and scalability.

![napkin-selection (1)](https://github.com/user-attachments/assets/bf60db6b-d235-4677-b860-42c9a7a695e5)


## HTTP Messages
HTTP messages are the mechanism used to exchange data between a server and a client in the HTTP protocol. There are two types of messages: requests sent by the client to trigger an action on the server, and responses, the answer that the server sends in response to a request.
![image](https://github.com/user-attachments/assets/499825e2-41e7-4eb8-acce-15b0be25f8cc)


**Both requests and responses share a similar structure:**

1) A start-line is a single line that describes the HTTP version along with the request method or the outcome of the request.
2) An optional set of HTTP headers containing metadata that describes the message. For example, a request for a resource might include the allowed formats of that resource, while the response might include headers to indicate the actual format returned.
3) An empty line indicating the metadata of the message is complete.
4) An optional body containing data associated with the message. This might be POST data to send to the server in a request, or some resource returned to the client in a response. Whether a message contains a body or not is determined by the start-line and HTTP headers.

### HTTP Request Startline 
```http
<method> <request-target> <protocol>
```
* method: is one of a set of defined words that describes the meaning of the request and the desired outcome.
* request-target: The request target is usually an absolute or relative URL, and is characterized by the context of the request. 
* protocol: The HTTP version, which defines the structure of the remaining message, acting as an indicator of the expected version to use for the response.

### HTTP responses Startline
```http
<protocol> <status-code> <status-text>
```

* protocol: The HTTP version of the remaining message.
* status-code: A numeric status code that indicates whether the request succeeded or failed. Common status codes are 200, 404, or 302.
* status-text: The status text is a brief, purely informational, textual description of the status code to help a human understand the HTTP message.
