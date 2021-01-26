import React, { Component } from 'react';
import CommitmentService from '../services/CommitmentService';

class ListCommitments extends Component {
   constructor(props) {
      super(props)

      this.state = {
         commitment: []
      }
   }

   componentDidMount(){
      CommitmentService.getCommitments().then((res=>{
         this.setState({commitment:res.data})
      }))
   }

   render() {
      return (
         <div>
            <h2 className="text-center">My commitments</h2>
            <div className="row">
               <table className="table table-striped table-bordered">
                  <thead>
                     <tr>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Name of the challenge</th>
                        <th>Done</th>
                        <th>Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     {
                        this.state.commitment.map(
                            commitment =>
                              <tr key={commitment.id}>
                                 <td> {commitment.descritption}</td>
                                 <td> {commitment.date}</td>
                                 <td> {commitment.challengeName}</td>
                                 <td> {commitment.done}</td>
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

export default ListCommitments;