import React, { Component } from 'react';
import CommitmentService from '../services/CommitmentService';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { withRouter } from 'react-router';

class CreateCommitment extends Component {
    constructor(props) {
        super(props)

        this.state = {
            description: '',
            date: '',
            challengeName: '',
            done: '',
        }
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeDateHandler = this.changeDateHandler.bind(this);
        this.changeChallengeNameHandler = this.changeChallengeNameHandler.bind(this);
        this.changeStatusHandler = this.changeStatusHandler.bind(this);
    }

    addCommitment = (e) => {
        e.preventDefault();
        let commitment = { description: this.state.description, date: this.state.date, challengeName: this.state.challengeName, done: this.state.done };
        console.log('commitment => ' + JSON.stringify(commitment));

        CommitmentService.CreateComitment(commitment).then(res => {
            this.props.history.push('/commitment');
        });
    }

    changeDescriptionHandler = (event) => {
        this.setState({ description: event.target.value })
    }

    changeDateHandler = (newDate) => {
        this.setState({ date: newDate })
    }

    changeChallengeNameHandler = (event) => {
        this.setState({ challengeName: event.target.value })
    }

    changeStatusHandler = (event) => {
        this.setState({ done: event.target.value })
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h2 className="text-center">Add New Commitment</h2>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>The description of the commitment:</label>
                                        <input name="description" className="form-control" value={this.state.description} onChange={this.changeDescriptionHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Date:</label>
                                        <DatePicker name="date" className="form-control" todayButton={"Today"} dateFormat="dd/MM/yyyy" selected={this.state.date}
                                            onChange={this.changeDateHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>The name of the challenge:</label>
                                        <input name="challengeName" className="form-control" value={this.state.challengeName} onChange={this.changeChallengeNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Status:</label>
                                        <input name="status" className="form-control" value={this.state.done} onChange={this.changeStatusHandler} />
                                    </div>
                                </form>
                            </div>

                        </div>

                    </div>
                </div>
                    <button className="btn btn-info" onClick={this.addCommitment.bind(this)}>Add New Commitment</button>
            </div>
        );
    }
}

export default withRouter(CreateCommitment);