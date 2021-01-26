import React, { Component } from 'react';
import mink from '../mink.png';
import { Link } from 'react-router-dom';

class HeaderComponent extends Component {

    render() {
        return (
            <div className="mink-image">
                <header>
                    <Link to="/">
                        <img src={mink} alt="Mink">
                        </img>
                    </Link>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;