import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const history = useHistory();

  const handleUsername = (event) => {
    const username = event.target.value;
    if (!username) {
      setErrorMessage('All fields are required.');
    }
    setUsername(username);
  }

  const handlePassword = (event) => {
    const password = event.target.value;
    if (!password) {
      setErrorMessage('All fields are required.');
    }
    setPassword(password);
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    const dataObj = {
      username, password,
    };
    const reqOptions = {
      method: 'POST',
      'Content-type': 'application/json',
      body: JSON.parse(dataObj),
    };
    await fetch('http://localhost:8080/login', reqOptions)
      .then((res) => {
        if (res.status === 200) {
          history.push('/');
        }
      });
  }

  return (
    <div className="card col-md-6 offset-md-3 offset-md-3">
      <p>{errorMessage}</p>
      <form>
        <label htmlFor="username">Name
    <input
            type="text"
            id="username"
            name="username"
            value={username}
            onChange={handleUsername}
            required
          />
        </label>
        <label htmlFor="password">Password
    <input
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={handlePassword}
            required
          />
        </label>
        <button
          type="submit"
          onClick={handleSubmit}>
          Submit
     </button>
      </form>
    </div>
  );
}

export default LoginForm;