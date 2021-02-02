import React, { Component } from 'react';
import ChallengeService from '../services/ChallengeService';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { withRouter } from 'react-router';

class UpdateChallenge extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: props.match.params.id,
            name: '',
            startDate: '',
            endDate: '',
            minimumCommitment: '',
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeStartDateHandler = this.changeStartDateHandler.bind(this);
        this.changeEndDateHandler = this.changeEndDateHandler.bind(this);
        this.changeMinCommitmentHandler = this.changeMinCommitmentHandler.bind(this);
        this.updateChallenge = this.updateChallenge.bind(this);
    }

    componentDidMount() {
        ChallengeService.getChallengeById(this.state.id).then((res) => {
            let challenge = res.data;
            this.setState({
                name: challenge.name,
                startDate: Date.parse(challenge.startDate),
                endDate: Date.parse(challenge.endDate),
                minimumCommitment: challenge.minimumCommitment
            });
        });
    }

    updateChallenge = (e) => {
        e.preventDefault();
        let challenge = {
            name: this.state.name,
            startDate: this.state.startDate,
            endDate: this.state.endDate,
            minimumCommitment: this.state.minimumCommitment
        };
        console.log('challenge => ' + JSON.stringify(challenge));

        ChallengeService.updateChallenge(challenge, this.state.id).then(res => {
            this.props.history.push('/challenge')
        });
    }

    changeNameHandler = (event) => {
        this.setState({ name: event.target.value })
    }

    changeStartDateHandler = (startDate) => {
        this.setState({ startDate: startDate })
    }

    changeEndDateHandler = (endDate) => {
        this.setState({ endDate: endDate })
    }

    changeMinCommitmentHandler = (event) => {
        this.setState({ minimumCommitment: event.target.value })
    }

    render() {
        return (
            <div>
                < h2 className="text-center" > Update Challenge </h2 >
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            < div
                                className="card-body" >
                                < form >
                                    < div
                                        className="form-group" >
                                        < label > The name of challenge:</label>
                                        < input
                                            name="name"
                                            className="form-control"
                                            value={this.state.name}
                                            onChange={this.changeNameHandler}
                                        />
                                    </div>
                                    < div
                                        className="form-group" >
                                        < label > Start Date:</label>
                                        < DatePicker
                                            name="startDate"
                                            className="form-control"
                                            todayButton={"Today"}
                                            dateFormat="dd/MM/yyyy"
                                            selected={this.state.startDate}
                                            onChange={this.changeStartDateHandler}
                                        />
                                    </div>
                                    < div
                                        className="form-group" >
                                        < label > End Date:</label>
                                        < DatePicker
                                            name="endDate"
                                            className="form-control"
                                            todayButton={"Today"}
                                            dateFormat="dd/MM/yyyy"
                                            selected={this.state.endDate}
                                            onChange={this.changeEndDateHandler}
                                        />
                                    </div>
                                    < div
                                        className="form-group" >
                                        < label > Number of minimum commitments:</label>
                                        < input
                                            name="minimumCommitments"
                                            className="form-control"
                                            value={this.state.minimumCommitment}
                                            onChange={this.changeMinCommitmentHandler}
                                        />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    < button className="btn btn-info"
                        onClick={this.updateChallenge.bind(this)} > Update Challenge </button>
                </div>

            </div>
        )
            ;
    }
}

export default withRouter(UpdateChallenge);