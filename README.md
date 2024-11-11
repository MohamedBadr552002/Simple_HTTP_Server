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
