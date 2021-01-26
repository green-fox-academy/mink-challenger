import React, { Component } from 'react';
import ChallengeService from '../services/ChallengeService';

class ListChallenges extends Component {
   constructor(props) {
      super(props)

      this.state = {
         challenges: []
      }
   }

   componentDidMount() {
      ChallengeService.getChallenges().then((res => {
         this.setState({ challenges: res.data })
      }))
   }

   render() {
      return (
         <div>
            <h2 className="text-center">List of challenges</h2>
            <div className="row">
               <table className="table table-striped table-bordered">
                  <thead>
                     <tr>
                        <th>Name</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Minimum commitments</th>
                        <th>Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     {
                        this.state.challenges.map(
                           challenge =>
                              <tr key={challenge.id}>
                                 <td> {challenge.name}</td>
                                 <td> {challenge.startDate}</td>
                                 <td> {challenge.endDate}</td>
                                 <td> {challenge.minimumCommitment}</td>
                              </tr>
                        )
                     }
                  </tbody>
               </table >
            </div >
         </div >
      );
   }
}

export default ListChallenges;