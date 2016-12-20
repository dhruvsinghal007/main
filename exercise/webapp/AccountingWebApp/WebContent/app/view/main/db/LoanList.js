Ext.define('Accounts.view.main.db.LoanList', {
    extend: 'Ext.grid.Panel',
	xtype: 'loanList',
    requires: [
        'Accounts.store.LoanStore'
    ],
    title: 'Loans',
    store: 'Accounts.store.LoanStore',
	
	initComponent : function(){
		
		this.editing = Ext.create('Ext.grid.plugin.RowEditing');
		
		Ext.apply(this,{
			plugins : [this.editing],
			width : '100%',
			height : 400,
			selType : 'checkboxmodel',
			viewConfig : 
			{
				stripeRows : true
			},
			selModel: {
				mode : 'MULTI'
			},
			
			height : 400,

			columns: [
				{ text: 'Id',  dataIndex: 'loanId', width : 40 },
				{ text: 'CustomerId',  dataIndex: 'custId', width : 100, editor : {allowBlank : true} },
				{ 
					text: 'Credit/Debit', 
					dataIndex: 'mode', 
					flex : 1,
					editor : 
					{
						xtype : 'combo',
						allowBlank: false,
						typeAhead : true,
						renderTo: Ext.getBody(),
						store : [
							['Debit', 'Debit'],
							['Credit', 'Credit']
						]
					}
				},{ 
					text: 'Date', 
					dataIndex: 'date', 
					flex : 1,
					editor : 
					{
						xtype : 'datefield',
						allowBlank : true,
						format : 'd/m/Y'
					},
					renderer : Ext.util.Format.dateRenderer('d/m/Y')
				},
				{ text: 'Amount', dataIndex: 'amount', flex: 1, editor : {allowBlank : true} }
			],
			
			tbar : [{
				xtype : 'tbtext',
				id : 'loantxt',
				text : 'New'
			}],
			
			bbar : [
			{
				text : 'Create',
				scope : this,
				handler : function(){
					//alert("Added");
					
					var loanStore = Ext.data.StoreManager.lookup('Accounts.store.LoanStore');
					
					var loanModel = Ext.create("Accounts.model.Loan");
					
					loanModel.set("amount",0);
					
					var current = new Date();
					current = Ext.Date.format(current,'dmY')
					loanModel.set("date",current);
					
					loanModel.set("custId",0);
					
					loanModel.set("mode","Debit");
					
					Ext.Ajax.request({
						url : '/AccountingWebApp/accounts/addLoan',
						method : 'POST',
						jsonData : {
							"mode" : loanModel.get("mode"),
							"amount" : loanModel.get("amount"),
							"date" : current,
							"custId" : loanModel.get("custId")
						},
						headers : {
							'Content-Type' : 'application/json'
						},
						success : function(response,request){
							// process server response here
							
							console.log(response);
							console.log(request);
							var resp = Ext.decode(response.responseText);
						
							if(resp != null){
								var acc = new Accounts.model.Loan(resp);
								//console.log(acc);
								loanModel.set("loanId",acc.get("loanId"));
								loanModel.set("custId",acc.get("custId"));
								loanModel.set("mode",acc.get("mode"));
								loanModel.set("amount",acc.get("amount"));
								loanModel.set("date",acc.get("date"));
							}
							else{
								Ext.Msg.alert('Status', resp.message);
							}

						},
						failure : function(response,request){
							console.log(request);
							Ext.Msg.alert('Status', 'Save Failed.');
						}	
					});
					this.editing.cancelEdit();
					loanStore.add(loanModel);
					loanStore.sync();
					this.editing.startEdit(loanModel);
				}
			},
			{
				text : 'Update',
				scope : this,
				handler : function(){
					//alert("Updated");
					
					var loans = this.getSelectionModel().getSelection();
					var loanStore = Ext.data.StoreManager.lookup('Accounts.store.LoanStore');
					var messageRow = Ext.getCmp('loantxt');
					
					var data = [];
					for(var i = 0 ; i < loans.length ; i++){	
						data[i] = new Object({
							loanId : loans[i].get("loanId"),
							custId : loans[i].get("custId"),
							date : Ext.Date.format(loans[i].get("date"), 'dmY'),
							mode : loans[i].get("mode"),
							amount : loans[i].get("amount")
						});
						
					}
					
					if (data.length > 0){
						Ext.Msg.show({
							title : 'Update',
							msg : 'Update these records? ',
							width : 300,
							closable : false,
							buttons : Ext.Msg.YESNO,
							icon : Ext.Msg.QUESTION,
							fn : function(buttonValue, inputText, showConfig){
								if(buttonValue === 'yes'){
									
									Ext.Ajax.request({
										url : '/AccountingWebApp/accounts/multiUpdateLoans',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											var resp = Ext.decode(response.responseText);
											
											var loans = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var loan = Ext.create('Accounts.model.Loan',resp[i]);
												
												loans[i] = loan;
											}
											
											loanStore.load(loans);
											
											if(resp != null){
												
												messageRow.setStyle({
													textAlign : 'center',
													color : 'red'
												});
												
												messageRow.setText('Updated Successfully');
												Ext.defer(function(){
													messageRow.getEl().setStyle({
														color : 'black'
													});
													messageRow.setText('Ready');
												},3000);
												
											}	
											else{
												Ext.Msg.alert('Status', 'Could not update. Try again.');
											}
										},
										failure : function(response){
											console.log(response);
											Ext.Msg.alert('Status', 'Request Failed.');
										  
										}
									});
								}
							}
					   
						});
					}
					else{
						Ext.Msg.alert('Status', 'Please select at least one record to update!');
					}
					
				}
			},
			{
				text : 'Delete',
				scope : this,
				handler : function(){
					//alert("Delete");
					var loanStore = Ext.data.StoreManager.lookup('Accounts.store.LoanStore');
					var loans = this.getSelectionModel().getSelection();
					var messageRow = Ext.getCmp('loantxt');
					
					var data = [];
					for(var i = 0 ; i < loans.length ; i++){
						
						data[i] = new Object({
							loanId : loans[i].get("loanId"),
							custId : loans[i].get("custId"),
							date : Ext.Date.format(loans[i].get("date"), 'dmY'),
							mode : loans[i].get("mode"),
							amount : loans[i].get("amount")
						});
					}
					
					if (data.length > 0){
						Ext.Msg.show({
							title : 'Delete',
							msg : 'Do you want to delete these records? ',
							width : 300,
							closable : false,
							buttons : Ext.Msg.YESNO,
							icon : Ext.Msg.QUESTION,
							fn : function(buttonValue, inputText, showConfig){
								if(buttonValue === 'yes'){
									
									Ext.Ajax.request({
										url : '/AccountingWebApp/accounts/multiDeleteLoans',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											messageRow.getEl().setStyle({
												textAlign : 'center',
												color : 'red'
											});
											
											messageRow.getEl().setText('Deleted Successfully');
											Ext.defer(function(){
												messageRow.getEl().setStyle({
													color : 'black'
												});
												messageRow.getEl().setText('Ready');
											},3000);
											
										},
										failure : function(response,request){
											console.log(request);
											console.log(response);
											Ext.Msg.alert('Status', 'Request Failed.');
										
										}
									});
									loanStore.remove(loans);
									loanStore.load();
								}
							}
						
						});
				   
						
					}
					else{
						Ext.Msg.alert('Status', 'Please select at least one record to delete!');
					}
				}
			},
			{
				xtype : 'pagingtoolbar',
				displayInfo: true,
				store : 'Accounts.store.LoanStore'
			}
			]
		});
		
		this.callParent();
		
	}
	
});
