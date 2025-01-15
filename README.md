# online-shop

This is an end-to-end back-end project where I designed and implemented RESTful APIs within a clean architecture framework. The project includes two main entities: User and Store.

I have 2 types of users, admin, and customer. Each of them has different permission:

Customer Features:

Customers can log in, log out, and sign up.
Customers can add items to their baskets and remove items from them.
Customers can edit the number of items within their basket.
Customers can finalize their purchases.

Admin Features:

Admins have different permissions and can manage the data repository.
Admins can sign in, sign out, and sign up.
Admins can remove items, update item information, or add/delete items as needed.

Example of Implemented APIs:
/api/v1/authentication/users
/api/v1/users/me/permissions

