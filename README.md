this is a client server RMI chat room, it allows communicating between many clients by dint of a central server. 
this latter manges coming connections, stores temporarily list of identified, indexed messages.Exchanging messages
is made by checking periodically for stored messages in the server. Acceding to the room requires an identification step:
actually there is just an Access file of one table that stores users nicknames and their related passwords.
