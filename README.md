Quickea the quick ikea app.

I chose a simple Clean architecture pattern to setup the project, having a data, a domain and a presentation layer.
For dependency injection I chose Hilt, for Unit tests I'm using jUnit and mockk.
In the data layer I separated:
- the API entities which are the amppings from the json file
- the local entities, which are for 'offline' data storage of the json in the Room database
- the repository which retrieves data either from the json api or the local db.
I've made a decision here because of the special file read, as I'm not getting the data every time from a server, I'm only updating the database if it's empty.
If there were real API calls, I'd change this caching so it always has the freshest data whenever there's internet connection.
The cart consists of CartItemEntities saved to their own database table, referencing the cached products.
This way the cart is always persisted for the user, even after leaving the app.
For the separation of concerns I added data transformers to map the model objects between layers.
In the API and Product entities I wanted to keep it simple with the different product infos as they only differ in one field,
so I opted for using simple maps to store the specific information per product type, but in the domain layer I map them to their own classes.
The domain layer consists of the model classes and the use cases for the app, adding removing cartitems, calculating data for the UI.
The presentation layer contains all the compose views and the viewmodels retrieving the data through use cases.

