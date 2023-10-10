import React, {Component} from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {}
    }

    render() {
        return (
            <div>
                <header>
                    <nav className='navbar navbar-expend-md navbar-dark bg-dark'>
                        <a style={{marginLeft: '20px'}} className='navbar-brand' href='http://localhost:3000/vehicles'>Transport
                            Management Service</a>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;