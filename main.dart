import 'package:flutter/material.dart';
void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    home: Scaffold(
      appBar: AppBar(
        centerTitle: true,
        backgroundColor: Colors.purpleAccent,
        title: const Text('Registration'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            const TextField(
              decoration: InputDecoration(
                labelText: 'Name',
                hintText: 'Enter full name',
              ),
            ),
            const TextField(
              decoration: InputDecoration(
                labelText: 'Mobile Number',
                hintText: 'Enter Mobile number',
              ),
            ),
            const TextField(
              decoration: InputDecoration(
                labelText: 'Email',
                hintText: 'Enter email',
              ),
            ),
            const TextField(
              obscureText: true,
              decoration: InputDecoration(
                labelText: 'Password',
                hintText: 'Enter password',
              ),
            ),
            const TextField(
              decoration: InputDecoration(
                labelText: 'Area of Interest',
                hintText: 'Enter Your Area of Interest (AI or ML) only',
              ),
            ),
            const SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: () {
                // this will showing in debug console
                print('Registration Successful');
              },
              child: const Text('Register'),
            ),
          ],
        ),
      ),
    ),
  ));
}