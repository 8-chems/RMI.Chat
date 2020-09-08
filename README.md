This application is a client-server RMI chat room. 
1) The app allows to communicate between many clients by meant of the central server. 
2) The server handels the next tasks: a) managing the coming connections, b) storing temporarily list of identified, indexed messages.
3) The messages exchanged between different clients are checked periodically from the stored messages list saved in the server. 
4) Acceding to the room requires an identification step: Actually, there is just an Access file of one table with users credential information ( users' nicknames and their related passwords).
