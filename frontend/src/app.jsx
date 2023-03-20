import React from 'react';
import ReactDOM from 'react-dom/client';

function formatName(user) {
    return user.firstName + ' ' + user.lastName;
}

const user = {
    firstName: 'Guilherme',
    lastName: 'Pereira'
};

const element = (
    <h1>
        Hello, {formatName(user)}!
    </h1>
);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(element);
