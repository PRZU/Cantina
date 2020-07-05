<template>
    <b-container class="pb-3" fluid>
        <pdf @loaded="getContent()" ref="myPdfComponent" src="/pdf.pdf"></pdf>
        <b-row>
            <b-table ref="table" :fields="fields" :items="items"></b-table>
        </b-row>
    </b-container>
</template>
 
<script>
 
import pdf from 'vue-pdf'
 
export default {
    name: 'PDFReader',
    components: {
        pdf
    },
    data(){
        return{
            fields: [],
            items: []
        }
    },
    methods: {
        getContent() {
            this.$refs.myPdfComponent.pdf.forEachPage( page => {
                page.getTextContent().then( res => {
                        const len = res.items.length
                        let i = 0
                        let week_days = 5
                        console.log(res.items)

                        // Dias da Semana
                        for(; i<len && i<=week_days; i++){
                            let str = res.items[i].str.trim()
                            let str2 = res.items[i+week_days].str.trim()
                            if(str.replace(/[ \t]/g, "") != '') 
                                this.fields.push({
                                    'key': str.toLowerCase(),
                                    'value': str+" "+str2
                                })
                        }

                        i+=week_days+1
                        let j=0
                        // Sopas
                        for(; i<len && res.items[i].str.trim().toLowerCase()!='prato'; i++){
                            let str = res.items[i].str
                            if(!this.items[0]){
                                this.items[0] = {}
                            }
                            
                            if(this.items[0][this.fields[j].key])
                                this.items[0][this.fields[j].key] += str 
                            else
                                this.items[0][this.fields[j].key] = str

                            if(!str.endsWith(" ")){
                                j++
                            }
                        }
                        i++
                        j = 0
                        // Pratos
                        for(; i<len && res.items[i].str.trim().toLowerCase()!='ementa'; i++){
                            let str = res.items[i].str
                            if(!this.items[1]){
                                this.items[1] = {}
                            }
                            
                            if(this.items[1][this.fields[j].key])
                                this.items[1][this.fields[j].key] += str 
                            else
                                this.items[1][this.fields[j].key] = str

                            if(!str.endsWith(" ")){
                                j++
                            }
                        }
                    this.$refs.table.refresh()
                })
            })
        }
    }
}
</script>